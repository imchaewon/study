import { useCallback, useEffect, useMemo, useState } from "react";
import { AgGridReact } from "ag-grid-react";
import type {
	CellClassParams,
	ColDef,
	ColGroupDef,
	ValueFormatterParams,
} from "ag-grid-community";

const PRESET_DAYS = [1, 5, 20, 60];
const PRESET_WEEKS = [1, 4, 13, 26, 52];

type AnalysisRow = Record<string, unknown>;
type AnalysisResponse = {
	baseDate: string;
	daysWindows: number[];
	weeksWindows: number[];
	rows: AnalysisRow[];
};

function todayBaseDate(): string {
	const now = new Date();
	const y = now.getFullYear();
	const m = String(now.getMonth() + 1).padStart(2, "0");
	const d = String(now.getDate()).padStart(2, "0");
	return `${y}${m}${d}`;
}

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

function buildColumnDefs(
	daysWindows: number[],
	weeksWindows: number[],
): (ColDef | ColGroupDef)[] {
	const baseColumns: ColDef[] = [
		{ field: "code", headerName: "종목", pinned: "left", width: 100 },
		{ field: "eIcod", headerName: "업종", width: 200 },
		{ field: "base", headerName: "현재가", type: "numericColumn", valueFormatter: fmtNum, width: 110 },
		{ field: "pvol", headerName: "거래량", type: "numericColumn", valueFormatter: fmtNum, width: 130 },
		{
			field: "neglectIndex",
			headerName: "소외지수",
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 110,
			cellClass: "font-medium",
		},
	];

	const buildPctCols = (
		prefix: string,
		labelSuffix: string,
		days: number[],
		weeks: number[],
	): ColDef[] => [
		...days.map<ColDef>((n) => ({
			field: `${prefix}_${n}d`,
			headerName: `${n}일 ${labelSuffix}`,
			type: "numericColumn",
			valueFormatter: fmtPct,
			cellClass: pctCellClass,
			width: 120,
		})),
		...weeks.map<ColDef>((n) => ({
			field: `${prefix}_${n}w`,
			headerName: `${n}주 ${labelSuffix}`,
			type: "numericColumn",
			valueFormatter: fmtPct,
			cellClass: pctCellClass,
			width: 120,
		})),
	];

	const buildNeglectCols = (days: number[], weeks: number[]): ColDef[] => [
		...days.map<ColDef>((n) => ({
			field: `neglectIndex_${n}d_ago`,
			headerName: `${n}일전 소외지수`,
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 130,
		})),
		...weeks.map<ColDef>((n) => ({
			field: `neglectIndex_${n}w_ago`,
			headerName: `${n}주전 소외지수`,
			type: "numericColumn",
			valueFormatter: fmtFixed2,
			width: 130,
		})),
	];

	return [
		...baseColumns,
		{ headerName: "가격 등락률", children: buildPctCols("priceChange", "등락률", daysWindows, weeksWindows) },
		{ headerName: "거래량 변동률", children: buildPctCols("volumeChange", "거래량변동", daysWindows, weeksWindows) },
		{ headerName: "N기간 전 소외지수", children: buildNeglectCols(daysWindows, weeksWindows) },
	];
}

export default function App() {
	const [baseDate, setBaseDate] = useState(todayBaseDate());
	const [selectedDays, setSelectedDays] = useState<number[]>(PRESET_DAYS);
	const [selectedWeeks, setSelectedWeeks] = useState<number[]>(PRESET_WEEKS);
	const [customDays, setCustomDays] = useState("");
	const [customWeeks, setCustomWeeks] = useState("");
	const [search, setSearch] = useState("");
	const [data, setData] = useState<AnalysisResponse | null>(null);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState<string | null>(null);

	const fetchData = useCallback(async () => {
		setLoading(true);
		setError(null);

		const allDays = uniqueSorted([...selectedDays, ...parseNList(customDays)]);
		const allWeeks = uniqueSorted([...selectedWeeks, ...parseNList(customWeeks)]);

		const params = new URLSearchParams();
		params.set("baseDate", baseDate);
		if (allDays.length) params.set("daysWindows", allDays.join(","));
		if (allWeeks.length) params.set("weeksWindows", allWeeks.join(","));

		try {
			const res = await fetch(`/api/analysis/overseas?${params.toString()}`);
			if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
			const json: AnalysisResponse = await res.json();
			setData(json);
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
		if (!search.trim()) return data.rows;
		const q = search.toUpperCase();
		return data.rows.filter((r) => {
			const code = String(r.code ?? "").toUpperCase();
			const sector = String(r.eIcod ?? "");
			return code.includes(q) || sector.includes(search);
		});
	}, [data, search]);

	const togglePreset = (n: number, kind: "d" | "w") => {
		const setter = kind === "d" ? setSelectedDays : setSelectedWeeks;
		setter((s) => (s.includes(n) ? s.filter((x) => x !== n) : uniqueSorted([...s, n])));
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

			<div className="flex-1 ag-theme-quartz">
				<AgGridReact<AnalysisRow>
					rowData={filteredRows}
					columnDefs={columnDefs}
					defaultColDef={{
						sortable: true,
						filter: true,
						resizable: true,
						minWidth: 90,
					}}
					enableCellTextSelection
					animateRows={false}
				/>
			</div>
		</div>
	);
}
