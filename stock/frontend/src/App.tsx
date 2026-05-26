import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { AgGridReact } from "ag-grid-react";
import type {
	CellClassParams,
	ColDef,
	ColGroupDef,
	ColumnState,
	SortChangedEvent,
	ValueFormatterParams,
} from "ag-grid-community";
import { MarketRegimePanel } from "./MarketRegimePanel";

const PRESET_DAYS = [1, 5, 20, 60];
const PRESET_WEEKS = [1, 4, 13, 26, 52];

type AnalysisRow = Record<string, unknown>;
type AnalysisResponse = {
	baseDate: string;
	daysWindows: number[];
	weeksWindows: number[];
	rows: AnalysisRow[];
};

// "1-5", "3,7,15", "1-3,10,20-22" 등을 양의 정수 배열로 파싱.
// 범위는 start <= end만 허용. 음수/NaN/역방향은 무시. 너무 큰 범위는 안전 상한 1000.
function parseNList(input: string): number[] {
	const out: number[] = [];
	for (const tokenRaw of input.split(",")) {
		const token = tokenRaw.trim();
		if (!token) continue;
		const rangeMatch = token.match(/^(\d+)\s*-\s*(\d+)$/);
		if (rangeMatch) {
			const start = parseInt(rangeMatch[1], 10);
			const end = parseInt(rangeMatch[2], 10);
			if (!isNaN(start) && !isNaN(end) && start > 0 && end >= start) {
				const cap = Math.min(end, start + 999);
				for (let i = start; i <= cap; i++) out.push(i);
			}
		} else {
			const n = parseInt(token, 10);
			if (!isNaN(n) && n > 0) out.push(n);
		}
	}
	return out;
}

function uniqueSorted(arr: number[]): number[] {
	return Array.from(new Set(arr)).sort((a, b) => a - b);
}

const fmtPct = (params: ValueFormatterParams) => {
	const v = params.value;
	return typeof v === "number" ? `${v.toFixed(2)}%` : "";
};

const fmtNum = (params: ValueFormatterParams) => {
	const v = params.value;
	return typeof v === "number" ? v.toLocaleString() : "";
};

const fmtFixed2 = (params: ValueFormatterParams) => {
	const v = params.value;
	return typeof v === "number" ? v.toFixed(2) : "";
};

const pctCellClass = (params: CellClassParams) => {
	const v = params.value;
	if (typeof v !== "number") return "";
	if (v > 0) return "text-red-600 font-medium";
	if (v < 0) return "text-blue-600 font-medium";
	return "";
};

const bullScoreCellClass = (params: CellClassParams) => {
	const v = params.value;
	if (typeof v !== "number") return "";
	if (v >= 85) return "bg-green-200 text-green-900 font-bold";
	if (v >= 70) return "bg-green-100 text-green-800 font-semibold";
	if (v >= 50) return "text-gray-700";
	if (v >= 30) return "text-blue-700";
	return "text-blue-900 font-medium";
};

const tripleBullCellClass = (params: CellClassParams) => {
	return params.value === true ? "bg-yellow-200 text-yellow-900 font-bold text-center" : "text-gray-300 text-center";
};

const fmtSignal = (params: ValueFormatterParams) => (params.value === true ? "⭐" : "·");
const fmtArranged = (params: ValueFormatterParams) => (params.value === true ? "정배열" : params.value === false ? "—" : "");

// 컬럼 그룹 사이 구분선 — 그룹의 첫 컬럼에 left border 적용.
// 기존 cellClass(함수/배열/문자열 모두)를 보존하면서 'group-start' class를 결합.
function withGroupStart(col: ColDef): ColDef {
	const existing = col.cellClass;
	let cellClass: ColDef["cellClass"];
	if (!existing) cellClass = "group-start";
	else if (typeof existing === "string") cellClass = [existing, "group-start"];
	else if (Array.isArray(existing)) cellClass = [...existing, "group-start"];
	else {
		const fn = existing as (p: CellClassParams) => string | string[] | undefined;
		cellClass = (params: CellClassParams) => {
			const r = fn(params);
			if (!r) return ["group-start"];
			if (typeof r === "string") return [r, "group-start"];
			return [...r, "group-start"];
		};
	}
	const headerClass = col.headerClass
		? Array.isArray(col.headerClass)
			? [...col.headerClass, "group-start"]
			: [String(col.headerClass), "group-start"]
		: "group-start";
	return { ...col, cellClass, headerClass };
}

const markFirst = (cols: ColDef[]): ColDef[] =>
	cols.length === 0 ? cols : [withGroupStart(cols[0]), ...cols.slice(1)];

function buildColumnDefs(
	daysWindows: number[],
	weeksWindows: number[],
): (ColDef | ColGroupDef)[] {
	const baseColumns: ColDef[] = [
		{
			field: "rank",
			headerName: "시총순위",
			pinned: "left",
			type: "numericColumn",
			width: 90,
			sort: "asc",
		},
		{ field: "code", headerName: "종목", pinned: "left", width: 100 },
		{
			field: "bullScore",
			headerName: "Bull Score",
			pinned: "left",
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			cellClass: bullScoreCellClass,
			width: 110,
			sort: "desc",
		},
		{
			field: "tripleBullSignal",
			headerName: "⭐ Triple Bull",
			pinned: "left",
			valueFormatter: fmtSignal,
			cellClass: tripleBullCellClass,
			width: 120,
			filter: true,
		},
		{ field: "eIcod", headerName: "업종", width: 200 },
		{ field: "base", headerName: "현재가", type: "numericColumn", valueFormatter: fmtNum, width: 110 },
		{ field: "pvol", headerName: "거래량", type: "numericColumn", valueFormatter: fmtNum, width: 130 },
		{
			field: "neglectIndex",
			headerName: "NI (소외지수)",
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 120,
			cellClass: "font-medium",
		},
		{ field: "rsi", headerName: "RSI(14)", type: "numericColumn", valueFormatter: fmtFixed2, width: 100 },
		{ field: "sma200", headerName: "SMA200", type: "numericColumn", valueFormatter: fmtFixed2, width: 110 },
		{ field: "arranged", headerName: "정배열", valueFormatter: fmtArranged, width: 90 },
	];

	const buildPctCols = (
		prefix: string,
		labelSuffix: string,
		days: number[],
		weeks: number[],
	): ColDef[] => [
		...days.map<ColDef>((n) => ({
			field: `${prefix}_${n}d`,
			headerName: `${n}일 후 ${labelSuffix}`,
			type: "numericColumn",
			valueFormatter: fmtPct,
			cellClass: pctCellClass,
			width: 130,
		})),
		...weeks.map<ColDef>((n) => ({
			field: `${prefix}_${n}w`,
			headerName: `${n}주 후 ${labelSuffix}`,
			type: "numericColumn",
			valueFormatter: fmtPct,
			cellClass: pctCellClass,
			width: 130,
		})),
	];

	const buildNeglectCols = (days: number[], weeks: number[]): ColDef[] => [
		...days.map<ColDef>((n) => ({
			field: `neglectIndex_${n}d_future`,
			headerName: `${n}일 후 소외지수`,
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 140,
		})),
		...weeks.map<ColDef>((n) => ({
			field: `neglectIndex_${n}w_future`,
			headerName: `${n}주 후 소외지수`,
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 140,
		})),
	];

	return [
		...baseColumns,
		{ headerName: "미래 가격 등락률", children: markFirst(buildPctCols("priceChange", "등락률", daysWindows, weeksWindows)) },
		{ headerName: "미래 거래량 변동률", children: markFirst(buildPctCols("volumeChange", "거래량변동", daysWindows, weeksWindows)) },
		{ headerName: "미래 소외지수", children: markFirst(buildNeglectCols(daysWindows, weeksWindows)) },
	];
}

export default function App() {
	const [baseDate, setBaseDate] = useState("");
	const [selectedDays, setSelectedDays] = useState<number[]>(PRESET_DAYS);
	const [selectedWeeks, setSelectedWeeks] = useState<number[]>(PRESET_WEEKS);
	const [customDays, setCustomDays] = useState("");
	const [customWeeks, setCustomWeeks] = useState("");
	const [search, setSearch] = useState("");
	const [topN, setTopN] = useState("");
	const [avgTopN, setAvgTopN] = useState("");
	const [relativeToAll, setRelativeToAll] = useState(false);
	const [data, setData] = useState<AnalysisResponse | null>(null);
	const headerContainerRef = useRef<HTMLDivElement>(null);
	const bodyContainerRef = useRef<HTMLDivElement>(null);
	const headerGridRef = useRef<AgGridReact<AnalysisRow>>(null);
	const bodyGridRef = useRef<AgGridReact<AnalysisRow>>(null);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState<string | null>(null);

	const fetchData = useCallback(async () => {
		setLoading(true);
		setError(null);

		const allDays = uniqueSorted([...selectedDays, ...parseNList(customDays)]);
		const allWeeks = uniqueSorted([...selectedWeeks, ...parseNList(customWeeks)]);

		const params = new URLSearchParams();
		if (baseDate.trim()) params.set("baseDate", baseDate.trim());
		if (allDays.length) params.set("daysWindows", allDays.join(","));
		if (allWeeks.length) params.set("weeksWindows", allWeeks.join(","));

		try {
			const res = await fetch(`/api/analysis/overseas?${params.toString()}`);
			if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
			const json: AnalysisResponse = await res.json();
			setData(json);
			// 서버가 결정한 baseDate를 입력칸에 반영 (비어 있던 경우)
			if (!baseDate.trim() && json.baseDate) setBaseDate(json.baseDate);
		} catch (e) {
			setError(e instanceof Error ? e.message : String(e));
		} finally {
			setLoading(false);
		}
	}, [baseDate, selectedDays, selectedWeeks, customDays, customWeeks]);

	useEffect(() => {
		fetchData();
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const columnDefs = useMemo(() => {
		if (!data) return [];
		return buildColumnDefs(data.daysWindows, data.weeksWindows);
	}, [data]);

	const filteredRows = useMemo(() => {
		if (!data) return [];
		const topNum = parseInt(topN.trim(), 10);
		const limitRank = !isNaN(topNum) && topNum > 0 ? topNum : null;
		const q = search.trim().toUpperCase();

		return data.rows.filter((r) => {
			if (limitRank !== null) {
				const rank = typeof r.rank === "number" ? r.rank : null;
				if (rank === null || rank > limitRank) return false;
			}
			if (q) {
				const code = String(r.code ?? "").toUpperCase();
				const sector = String(r.eIcod ?? "");
				if (!code.includes(q) && !sector.includes(search)) return false;
			}
			return true;
		});
	}, [data, search, topN]);

	// 본문 그리드에 실제로 표시할 row.
	// relativeToAll이 켜지면 numeric 컬럼을 filteredRows 전체 평균(baseline) 대비 차이로 변환.
	// 또 가격 등락률(priceChange_*) 컬럼별로 max/min row를 찾아 __maxKeys/__minKeys 표시 → cellStyle에서 색칠.
	const displayedRows = useMemo<AnalysisRow[]>(() => {
		if (filteredRows.length === 0) return filteredRows;

		// 1) relativeToAll 변환 — 미래 가격 등락률(priceChange_*) + 거래량 변동률(volumeChange_*)을 baseline 대비 차이로 변환.
		//    다른 컬럼(미래 소외지수, RSI, 절대 가격 등)은 절대값 유지.
		let rows: AnalysisRow[];
		if (relativeToAll) {
			const baseline: Record<string, number> = {};
			const baseCnt: Record<string, number> = {};
			for (const row of filteredRows) {
				for (const [k, v] of Object.entries(row)) {
					if (!k.startsWith("priceChange_") && !k.startsWith("volumeChange_")) continue;
					if (typeof v === "number" && isFinite(v)) {
						baseline[k] = (baseline[k] ?? 0) + v;
						baseCnt[k] = (baseCnt[k] ?? 0) + 1;
					}
				}
			}
			for (const k of Object.keys(baseline)) baseline[k] /= baseCnt[k];

			rows = filteredRows.map((row) => {
				const out: AnalysisRow = { ...row };
				for (const [k, v] of Object.entries(out)) {
					if (typeof v !== "number") continue;
					if (!(k in baseline)) continue;
					out[k] = v - baseline[k];
				}
				return out;
			});
		} else {
			// 색칠 표시를 위해 row 객체 얕은 복사 (원본 filteredRows 안 건드림)
			rows = filteredRows.map((row) => ({ ...row }));
		}

		// 2) 종목 row 내부에서 가격 등락률(priceChange_*) 컬럼들 중 max/min 컬럼 찾기.
		//    각 종목이 어느 기간에 가장 잘 올랐고/못 올랐는지를 row 안에서 표시.
		const priceChangeKeys = Object.keys(rows[0]).filter((k) => k.startsWith("priceChange_"));
		let globalMaxVal = -Infinity;
		let globalMinVal = Infinity;
		let globalMaxRow: AnalysisRow | null = null;
		let globalMaxKey: string | null = null;
		let globalMinRow: AnalysisRow | null = null;
		let globalMinKey: string | null = null;
		for (const row of rows) {
			let maxVal = -Infinity;
			let minVal = Infinity;
			let maxKey: string | null = null;
			let minKey: string | null = null;
			for (const key of priceChangeKeys) {
				const v = row[key];
				if (typeof v !== "number" || !isFinite(v)) continue;
				if (v > maxVal) { maxVal = v; maxKey = key; }
				if (v < minVal) { minVal = v; minKey = key; }
				// 전체 풀 (모든 종목 × 모든 기간)의 단일 max/min
				if (v > globalMaxVal) { globalMaxVal = v; globalMaxRow = row; globalMaxKey = key; }
				if (v < globalMinVal) { globalMinVal = v; globalMinRow = row; globalMinKey = key; }
			}
			row.__maxKeys = new Set<string>();
			row.__minKeys = new Set<string>();
			if (maxKey && minKey && maxVal !== minVal) {
				(row.__maxKeys as Set<string>).add(maxKey);
				(row.__minKeys as Set<string>).add(minKey);
			}
		}
		if (globalMaxRow && globalMaxKey) globalMaxRow.__globalMaxKey = globalMaxKey;
		if (globalMinRow && globalMinKey) globalMinRow.__globalMinKey = globalMinKey;
		return rows;
	}, [filteredRows, relativeToAll]);

	// 그리드 최상단에 고정 표시할 평균 행들.
	// avgTopN에 콤마로 여러 값 입력 가능: "10,30,100" → 3개 평균 행.
	// 각각 BullScore 내림차순 상위 N개로 평균. 비우면 filteredRows 전체로 1개 행.
	// 각 numeric 컬럼별로 행 간 max는 __maxKeys, min은 __minKeys에 저장 → 셀 배경 하이라이트.
	const averageRows = useMemo<AnalysisRow[]>(() => {
		if (!data || filteredRows.length === 0) return [];

		const ns = uniqueSorted(parseNList(avgTopN));
		// 정렬한 사본 한 번만 만들기
		const sortedByBull = [...filteredRows].sort((a, b) => {
			const sa = typeof a.bullScore === "number" ? a.bullScore : -Infinity;
			const sb = typeof b.bullScore === "number" ? b.bullScore : -Infinity;
			return sb - sa;
		});

		const buildRow = (n: number | null): AnalysisRow => {
			const target = n !== null ? sortedByBull.slice(0, n) : filteredRows;
			const numericKeys = new Set<string>();
			for (const row of target) {
				for (const [k, v] of Object.entries(row)) {
					if (typeof v === "number") numericKeys.add(k);
				}
			}
			const label = n !== null ? `BS${target.length}` : `ALL`;
			const result: AnalysisRow = { code: label };
			for (const key of numericKeys) {
				let sum = 0;
				let cnt = 0;
				for (const row of target) {
					const v = row[key];
					if (typeof v === "number" && isFinite(v)) {
						sum += v;
						cnt += 1;
					}
				}
				if (cnt > 0) result[key] = sum / cnt;
			}
			delete result.rank;
			return result;
		};

		// avgTopN 비어있으면 ALL 평균만 표시 (토글 OFF/ON 무관).
		// 토글 ON일 때는 baseline(ALL 절대값)을 항상 맨 위에 두고, 그 아래 사용자 입력 행을 vs ALL Agerage로 변환.
		const userRows = ns.length > 0
			? ns.map((n) => buildRow(n))
			: (relativeToAll ? [] : [buildRow(null)]);

		let rows: AnalysisRow[];
		if (relativeToAll) {
			const baseline = buildRow(null); // ALL 절대값 — 변환 없이 그대로 첫 행에 표시
			for (const r of userRows) {
				r.code = `${r.code} vs ALL Agerage`;
				for (const [k, v] of Object.entries(r)) {
					if (typeof v !== "number") continue;
					if (!k.startsWith("priceChange_") && !k.startsWith("volumeChange_")) continue; // 미래 가격/거래량 변동률만 변환
					const base = baseline[k];
					if (typeof base !== "number") continue;
					r[k] = v - base;
				}
			}
			rows = [baseline, ...userRows];
		} else {
			rows = userRows;
		}

		// 컬럼별 max/min 행 찾기 (행이 2개 이상일 때만)
		if (rows.length >= 2) {
			const allKeys = new Set<string>();
			for (const r of rows) {
				for (const [k, v] of Object.entries(r)) {
					if (typeof v === "number") allKeys.add(k);
				}
			}
			for (const r of rows) {
				r.__maxKeys = new Set<string>();
				r.__minKeys = new Set<string>();
			}
			for (const key of allKeys) {
				let maxVal = -Infinity;
				let minVal = Infinity;
				let maxRow: AnalysisRow | null = null;
				let minRow: AnalysisRow | null = null;
				for (const r of rows) {
					const v = r[key];
					if (typeof v !== "number" || !isFinite(v)) continue;
					if (v > maxVal) { maxVal = v; maxRow = r; }
					if (v < minVal) { minVal = v; minRow = r; }
				}
				// max == min이면 (모든 행이 같은 값) 색칠 안 함
				if (maxRow && minRow && maxVal !== minVal) {
					(maxRow.__maxKeys as Set<string>).add(key);
					(minRow.__minKeys as Set<string>).add(key);
				}
			}
		}
		return rows;
	}, [data, filteredRows, avgTopN, relativeToAll]);

	const togglePreset = (n: number, kind: "d" | "w") => {
		const setter = kind === "d" ? setSelectedDays : setSelectedWeeks;
		setter((s) => (s.includes(n) ? s.filter((x) => x !== n) : uniqueSorted([...s, n])));
	};

	const toggleAllPreset = (kind: "d" | "w") => {
		const preset = kind === "d" ? PRESET_DAYS : PRESET_WEEKS;
		const current = kind === "d" ? selectedDays : selectedWeeks;
		const setter = kind === "d" ? setSelectedDays : setSelectedWeeks;
		const allSelected = preset.every((n) => current.includes(n));
		setter(allSelected ? [] : [...preset]);
	};

	const onSubmitKey = (e: React.KeyboardEvent<HTMLInputElement>) => {
		if (e.key === "Enter") {
			e.preventDefault();
			fetchData();
		}
	};

	// vs ALL Agerage 토글 시 본문 그리드의 가로 스크롤 위치 보존.
	// AG Grid가 rowData 처리 직후/렌더 후/layout 후 여러 단계에서 scrollLeft를 0으로 만들 수 있어
	// 여러 시점에 반복 복원 (한 번이라도 늦으면 reset됨).
	const handleToggleRelative = useCallback(() => {
		const findScroller = (root: HTMLElement | null): HTMLElement | null =>
			root?.querySelector<HTMLElement>(
				".ag-body-horizontal-scroll-viewport, .ag-center-cols-viewport, .ag-body-viewport",
			) ?? null;
		const savedLeft = findScroller(bodyContainerRef.current)?.scrollLeft ?? 0;
		setRelativeToAll((v) => !v);

		const restore = () => {
			const targets: HTMLElement[] = [
				findScroller(bodyContainerRef.current),
				findScroller(headerContainerRef.current),
				findScroller(document.querySelector(".border-yellow-300")), // 평균 그리드 wrapper
			].filter((el): el is HTMLElement => el !== null);
			for (const el of targets) {
				if (el.scrollLeft !== savedLeft) el.scrollLeft = savedLeft;
			}
		};
		// AG Grid의 다단계 layout 동안 여러 번 강제 복원
		for (const delay of [0, 50, 150, 300, 600]) {
			setTimeout(restore, delay);
		}
	}, []);

	return (
		<div className="flex flex-col h-screen bg-gray-50">
			<header className="px-4 py-3 border-b bg-white shadow-sm flex flex-wrap gap-3 items-end">
				<h1 className="text-lg font-semibold mr-3 self-center">해외주식 분석</h1>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					기준일
					<input
						type="text"
						value={baseDate}
						onChange={(e) => setBaseDate(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="YYYYMMDD"
						className="border rounded px-2 py-1 w-28 text-sm"
					/>
				</label>

				<div className="flex flex-col gap-1">
					<span className="text-xs text-gray-600">N일 프리셋</span>
					<div className="flex gap-1">
						<button
							onClick={() => toggleAllPreset("d")}
							className="px-2 py-1 text-sm rounded border bg-gray-100 border-gray-400 hover:bg-gray-200"
							title="전체 선택/해제"
						>
							{PRESET_DAYS.every((n) => selectedDays.includes(n)) ? "전체해제" : "전체선택"}
						</button>
						{PRESET_DAYS.map((n) => (
							<button
								key={n}
								onClick={() => togglePreset(n, "d")}
								className={`px-2 py-1 text-sm rounded border ${
									selectedDays.includes(n)
										? "bg-blue-500 text-white border-blue-500"
										: "bg-white border-gray-300 hover:bg-gray-100"
								}`}
							>
								{n}일
							</button>
						))}
					</div>
				</div>

				<div className="flex flex-col gap-1">
					<span className="text-xs text-gray-600">N주 프리셋</span>
					<div className="flex gap-1">
						<button
							onClick={() => toggleAllPreset("w")}
							className="px-2 py-1 text-sm rounded border bg-gray-100 border-gray-400 hover:bg-gray-200"
							title="전체 선택/해제"
						>
							{PRESET_WEEKS.every((n) => selectedWeeks.includes(n)) ? "전체해제" : "전체선택"}
						</button>
						{PRESET_WEEKS.map((n) => (
							<button
								key={n}
								onClick={() => togglePreset(n, "w")}
								className={`px-2 py-1 text-sm rounded border ${
									selectedWeeks.includes(n)
										? "bg-blue-500 text-white border-blue-500"
										: "bg-white border-gray-300 hover:bg-gray-100"
								}`}
							>
								{n}주
							</button>
						))}
					</div>
				</div>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					커스텀 일 (콤마/범위)
					<input
						type="text"
						value={customDays}
						onChange={(e) => setCustomDays(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="1-10, 15, 30"
						className="border rounded px-2 py-1 w-36 text-sm"
					/>
				</label>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					커스텀 주 (콤마/범위)
					<input
						type="text"
						value={customWeeks}
						onChange={(e) => setCustomWeeks(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="1-4, 8"
						className="border rounded px-2 py-1 w-36 text-sm"
					/>
				</label>

				<button
					onClick={fetchData}
					disabled={loading}
					className="px-3 py-1.5 bg-green-600 text-white rounded text-sm disabled:opacity-50 hover:bg-green-700"
				>
					{loading ? "조회중..." : "적용"}
				</button>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					시총 TOP N (표시)
					<input
						type="number"
						min="1"
						value={topN}
						onChange={(e) => setTopN(e.target.value)}
						placeholder="전체"
						className="border rounded px-2 py-1 w-24 text-sm"
					/>
				</label>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					평균: BullScore 상위 N (콤마/범위)
					<input
						type="text"
						value={avgTopN}
						onChange={(e) => setAvgTopN(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="1-10, 30, 100"
						className="border rounded px-2 py-1 w-40 text-sm bg-yellow-50"
					/>
				</label>

				<label
					className="flex flex-col gap-1 text-xs text-gray-600 cursor-pointer"
					title="ON: 각 평균 행을 '전체 평균(ALL) 대비 차이(%p)'로 표시 — 시장 추세 효과를 제거한 순수 그룹 알파"
				>
					vs ALL Agerage
					<button
						type="button"
						onClick={handleToggleRelative}
						className={`px-2 py-1 text-sm rounded border ${
							relativeToAll
								? "bg-purple-500 text-white border-purple-500"
								: "bg-white border-gray-300 hover:bg-gray-100"
						}`}
					>
						{relativeToAll ? "ON" : "OFF"}
					</button>
				</label>

				<input
					type="text"
					value={search}
					onChange={(e) => setSearch(e.target.value)}
					placeholder="종목/업종 검색"
					className="border rounded px-2 py-1 text-sm ml-auto w-48"
				/>
			</header>

			{error && (
				<div className="px-4 py-2 bg-red-100 text-red-700 text-sm">에러: {error}</div>
			)}

			<MarketRegimePanel baseDate={baseDate} />

			<HeaderOnlyGrid
				containerRef={headerContainerRef}
				gridRef={headerGridRef}
				columnDefs={columnDefs}
				bodyGridRef={bodyGridRef}
			/>

			<AverageGridPanel
				rows={averageRows}
				columnDefs={columnDefs}
				headerContainerRef={headerContainerRef}
				bodyContainerRef={bodyContainerRef}
			/>

			<div ref={bodyContainerRef} className="flex-1 ag-theme-quartz">
				<AgGridReact<AnalysisRow>
					ref={bodyGridRef}
					rowData={loading || !data ? undefined : displayedRows}
					headerHeight={0}
					groupHeaderHeight={0}
					defaultColDef={{
						sortable: true,
						filter: false,
						resizable: false,
						minWidth: 90,
						cellStyle: (params) => {
							const field = params.colDef.field;
							if (!field) return null;
							const row = params.data as AnalysisRow;
							// 전체 풀에서 단일 max/min — 진한 색 + 굵게 (가장 우선)
							if (row.__globalMaxKey === field) {
								return { background: "#ec4899", color: "white", fontWeight: 700 } as Record<string, string | number>;
							}
							if (row.__globalMinKey === field) {
								return { background: "#0ea5e9", color: "white", fontWeight: 700 } as Record<string, string | number>;
							}
							// 종목 row 내부 max/min — 연한 색
							if (row.__maxKeys instanceof Set && row.__maxKeys.has(field)) {
								return { background: "#fbcfe8", fontWeight: 600 } as Record<string, string | number>;
							}
							if (row.__minKeys instanceof Set && row.__minKeys.has(field)) {
								return { background: "#bae6fd", fontWeight: 600 } as Record<string, string | number>;
							}
							return null;
						},
					}}
					columnDefs={columnDefs}
					enableCellTextSelection
					animateRows={false}
					overlayLoadingTemplate={
						'<div class="flex flex-col items-center gap-3 text-gray-600">' +
						'<div class="w-10 h-10 border-4 border-gray-200 border-t-blue-500 rounded-full animate-spin"></div>' +
						'<span class="text-sm font-medium">데이터를 불러오는 중...</span>' +
						'</div>'
					}
					overlayNoRowsTemplate={
						'<div class="text-gray-500 text-sm">조건에 맞는 종목이 없습니다</div>'
					}
				/>
			</div>
		</div>
	);
}

/**
 * 본문 그리드의 헤더만 별도로 떼어낸 그리드.
 * rowData=[] 이지만 헤더는 정상 표시되며, 헤더에서 정렬 클릭하면 본문 그리드의 정렬을 동기화.
 */
function HeaderOnlyGrid({
	containerRef,
	gridRef,
	columnDefs,
	bodyGridRef,
}: {
	containerRef: React.RefObject<HTMLDivElement>;
	gridRef: React.RefObject<AgGridReact<AnalysisRow>>;
	columnDefs: (ColDef | ColGroupDef)[];
	bodyGridRef: React.RefObject<AgGridReact<AnalysisRow>>;
}) {
	// 헤더 그리드의 정렬 변경 → 본문 그리드에 동일 ColumnState 적용
	const onSortChanged = (e: SortChangedEvent<AnalysisRow>) => {
		const state: ColumnState[] = e.api.getColumnState();
		bodyGridRef.current?.api?.applyColumnState({
			state: state.map((s) => ({ colId: s.colId, sort: s.sort, sortIndex: s.sortIndex })),
			defaultState: { sort: null },
		});
	};

	return (
		<div ref={containerRef} className="ag-theme-quartz hide-h-scroll">
			<AgGridReact<AnalysisRow>
				ref={gridRef}
				rowData={[]}
				columnDefs={columnDefs}
				domLayout="autoHeight"
				defaultColDef={{
					sortable: true,
					filter: false,
					resizable: false,
					minWidth: 90,
				}}
				suppressCellFocus
				suppressNoRowsOverlay
				onSortChanged={onSortChanged}
				animateRows={false}
			/>
		</div>
	);
}

/**
 * 평균 행을 위한 별도 AG Grid.
 * - 헤더 숨김 (headerHeight=0)
 * - 행 수에 따라 height 동적 (1~3행 자연 높이, 그 이상은 220px max + 세로 스크롤)
 * - 메인 그리드와 가로 스크롤 동기화
 */
function AverageGridPanel({
	rows,
	columnDefs,
	headerContainerRef,
	bodyContainerRef,
}: {
	rows: AnalysisRow[];
	columnDefs: (ColDef | ColGroupDef)[];
	headerContainerRef: React.RefObject<HTMLDivElement>;
	bodyContainerRef: React.RefObject<HTMLDivElement>;
}) {
	const containerRef = useRef<HTMLDivElement>(null);

	// 3개 그리드(헤더/평균/본문) 가로 스크롤 동기화
	useEffect(() => {
		const findScroller = (root: HTMLElement | null): HTMLElement | null => {
			if (!root) return null;
			return (
				root.querySelector<HTMLElement>(".ag-body-horizontal-scroll-viewport") ??
				root.querySelector<HTMLElement>(".ag-center-cols-viewport") ??
				root.querySelector<HTMLElement>(".ag-body-viewport")
			);
		};
		let cleanup = () => {};
		const t = setTimeout(() => {
			const header = findScroller(headerContainerRef.current);
			const avg = rows.length > 0 ? findScroller(containerRef.current) : null;
			const body = findScroller(bodyContainerRef.current);
			const targets = [header, avg, body].filter(
				(el): el is HTMLElement => el !== null,
			);
			if (targets.length < 2) return;
			let syncing = false;
			const onScroll = (source: HTMLElement) => () => {
				if (syncing) return;
				syncing = true;
				for (const t of targets) {
					if (t !== source && t.scrollLeft !== source.scrollLeft) {
						t.scrollLeft = source.scrollLeft;
					}
				}
				queueMicrotask(() => { syncing = false; });
			};
			const handlers: Array<[HTMLElement, EventListener]> = targets.map((t) => [
				t,
				onScroll(t) as EventListener,
			]);
			for (const [el, h] of handlers) el.addEventListener("scroll", h);
			cleanup = () => {
				for (const [el, h] of handlers) el.removeEventListener("scroll", h);
			};
		}, 200);
		return () => {
			clearTimeout(t);
			cleanup();
		};
	}, [rows.length, headerContainerRef, bodyContainerRef]);

	if (rows.length === 0) return null;

	// domLayout=autoHeight → AG Grid가 row 수만큼 정확히 자동 높이 (1줄이면 1줄 높이, 세로 스크롤 없음).
	// wrapper max-height 50vh — 화면의 절반까지 자라고 그 이상은 자체 스크롤.
	return (
		<div
			ref={containerRef}
			className="ag-theme-quartz border-b-2 border-yellow-300 hide-h-scroll"
			style={{ maxHeight: "50vh", overflowY: "auto" }}
		>
			<AgGridReact<AnalysisRow>
				rowData={rows}
				columnDefs={columnDefs}
				headerHeight={0}
				domLayout="autoHeight"
				defaultColDef={{
					sortable: false,
					filter: false,
					resizable: false,
					minWidth: 90,
					cellStyle: (params) => {
						const field = params.colDef.field;
						if (!field) return { background: "#fef3c7", fontWeight: 600 };
						const row = params.data as AnalysisRow;
						if (row.__maxKeys instanceof Set && row.__maxKeys.has(field)) {
							return { background: "#fbcfe8", fontWeight: 700 };
						}
						if (row.__minKeys instanceof Set && row.__minKeys.has(field)) {
							return { background: "#bae6fd", fontWeight: 700 };
						}
						return { background: "#fef3c7", fontWeight: 600 };
					},
				}}
				suppressCellFocus
				animateRows={false}
			/>
		</div>
	);
}
