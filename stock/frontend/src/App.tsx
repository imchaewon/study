import { useCallback, useEffect, useMemo, useState } from "react";
import { AgGridReact } from "ag-grid-react";
import type {
	CellClassParams,
	ColDef,
	ColGroupDef,
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

function parseNList(input: string): number[] {
	return input
		.split(",")
		.map((s) => parseInt(s.trim(), 10))
		.filter((n) => !isNaN(n) && n > 0);
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
		{ headerName: "미래 가격 등락률", children: buildPctCols("priceChange", "등락률", daysWindows, weeksWindows) },
		{ headerName: "미래 거래량 변동률", children: buildPctCols("volumeChange", "거래량변동", daysWindows, weeksWindows) },
		{ headerName: "미래 소외지수", children: buildNeglectCols(daysWindows, weeksWindows) },
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
	const [data, setData] = useState<AnalysisResponse | null>(null);
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

	// 그리드 최상단에 고정 표시할 평균 행.
	// 대상: filteredRows를 bullScore 내림차순 정렬 후 상위 avgTopN개. avgTopN 비우면 filteredRows 전체.
	// bullScore가 null인 row는 정렬상 뒤로 밀림.
	const averageRow = useMemo<AnalysisRow | null>(() => {
		if (!data || filteredRows.length === 0) return null;

		const avgNum = parseInt(avgTopN.trim(), 10);
		const limit = !isNaN(avgNum) && avgNum > 0 ? avgNum : null;
		const targetRows = limit !== null
			? [...filteredRows]
					.sort((a, b) => {
						const sa = typeof a.bullScore === "number" ? a.bullScore : -Infinity;
						const sb = typeof b.bullScore === "number" ? b.bullScore : -Infinity;
						return sb - sa;
					})
					.slice(0, limit)
			: filteredRows;

		const numericKeys = new Set<string>();
		for (const row of targetRows) {
			for (const [k, v] of Object.entries(row)) {
				if (typeof v === "number") numericKeys.add(k);
			}
		}
		const label = limit !== null
			? `평균 (Bull Score 상위 ${targetRows.length}종목)`
			: `평균 (전체 ${targetRows.length}종목)`;
		const result: AnalysisRow = { code: label };
		for (const key of numericKeys) {
			let sum = 0;
			let cnt = 0;
			for (const row of targetRows) {
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
	}, [data, filteredRows, avgTopN]);

	const togglePreset = (n: number, kind: "d" | "w") => {
		const setter = kind === "d" ? setSelectedDays : setSelectedWeeks;
		setter((s) => (s.includes(n) ? s.filter((x) => x !== n) : uniqueSorted([...s, n])));
	};

	const onSubmitKey = (e: React.KeyboardEvent<HTMLInputElement>) => {
		if (e.key === "Enter") {
			e.preventDefault();
			fetchData();
		}
	};

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
					커스텀 일 (콤마)
					<input
						type="text"
						value={customDays}
						onChange={(e) => setCustomDays(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="3,7,15"
						className="border rounded px-2 py-1 w-28 text-sm"
					/>
				</label>

				<label className="flex flex-col gap-1 text-xs text-gray-600">
					커스텀 주 (콤마)
					<input
						type="text"
						value={customWeeks}
						onChange={(e) => setCustomWeeks(e.target.value)}
						onKeyDown={onSubmitKey}
						placeholder="2,8"
						className="border rounded px-2 py-1 w-28 text-sm"
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
					평균: BullScore 상위 N
					<input
						type="number"
						min="1"
						value={avgTopN}
						onChange={(e) => setAvgTopN(e.target.value)}
						placeholder="전체"
						className="border rounded px-2 py-1 w-28 text-sm bg-yellow-50"
					/>
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

			<div className="flex-1 ag-theme-quartz">
				<AgGridReact<AnalysisRow>
					rowData={loading || !data ? undefined : filteredRows}
					pinnedTopRowData={averageRow ? [averageRow] : undefined}
					getRowStyle={(params) =>
						params.node.rowPinned === "top"
							? { background: "#fef3c7", fontWeight: 600 }
							: undefined
					}
					columnDefs={columnDefs}
					defaultColDef={{
						sortable: true,
						filter: true,
						resizable: true,
						minWidth: 90,
					}}
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
