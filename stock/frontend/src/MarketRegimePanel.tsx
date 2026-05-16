import { useEffect, useState } from "react";

type RegimeKey =
	| "BULL_TREND_LOW_VOL"
	| "BULL_HIGH_VOL"
	| "BEAR_SIDEWAYS"
	| "BEAR_HIGH_VOL"
	| "UNKNOWN";

type RegimeSnapshot = {
	baseDate: string;
	indexValue: number;
	sma200: number | null;
	deviationPct: number | null;
	vol20Pct: number | null;
	regime: RegimeKey;
	regimeLabel: string;
};

type MarketRegimeResponse = {
	baseDate: string;
	current: RegimeSnapshot | null;
	history: RegimeSnapshot[];
};

const regimeColors: Record<RegimeKey, { bg: string; border: string; text: string }> = {
	BULL_TREND_LOW_VOL: { bg: "bg-green-50", border: "border-green-500", text: "text-green-700" },
	BULL_HIGH_VOL: { bg: "bg-yellow-50", border: "border-yellow-500", text: "text-yellow-700" },
	BEAR_SIDEWAYS: { bg: "bg-orange-50", border: "border-orange-500", text: "text-orange-700" },
	BEAR_HIGH_VOL: { bg: "bg-red-50", border: "border-red-500", text: "text-red-700" },
	UNKNOWN: { bg: "bg-gray-50", border: "border-gray-400", text: "text-gray-700" },
};

const regimeAdvice: Record<RegimeKey, string> = {
	BULL_TREND_LOW_VOL: "모멘텀 전략 활성화. NI≥75 매수 / NI<25 회피.",
	BULL_HIGH_VOL: "강세 유지하되 변동성 대비. 포지션 사이즈 줄이기 권장.",
	BEAR_SIDEWAYS: "long-only 위험. 시장중립 LS 또는 현금 권장.",
	BEAR_HIGH_VOL: "약세 + 고변동. NI<25 short 또는 현금 100%.",
	UNKNOWN: "데이터 부족 — 보수적 접근.",
};

function fmt(v: number | null, suffix = "", decimals = 2): string {
	if (v === null || v === undefined) return "N/A";
	const sign = suffix === "%" && v >= 0 ? "+" : "";
	return `${sign}${v.toFixed(decimals)}${suffix}`;
}

function colorForDev(v: number | null): string {
	if (v === null) return "";
	return v >= 0 ? "text-red-600" : "text-blue-600";
}

function colorForVol(v: number | null): string {
	if (v === null) return "";
	return v > 25 ? "text-orange-600" : "text-green-600";
}

export function MarketRegimePanel({ baseDate }: { baseDate: string }) {
	const [data, setData] = useState<MarketRegimeResponse | null>(null);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState<string | null>(null);
	const [showHistory, setShowHistory] = useState(false);

	useEffect(() => {
		// baseDate가 비었으면 서버가 DB 최신 날짜로 채워줌. 유효한 YYYYMMDD거나 빈값일 때만 호출.
		const trimmed = baseDate.trim();
		if (trimmed && !/^\d{8}$/.test(trimmed)) return;
		setLoading(true);
		setError(null);
		const params = new URLSearchParams({ historyDays: "30" });
		if (trimmed) params.set("baseDate", trimmed);
		fetch(`/api/analysis/market-regime?${params.toString()}`)
			.then((r) => {
				if (!r.ok) throw new Error(`${r.status}`);
				return r.json();
			})
			.then(setData)
			.catch((e) => setError(e instanceof Error ? e.message : String(e)))
			.finally(() => setLoading(false));
	}, [baseDate]);

	if (loading && !data) {
		return <div className="px-4 py-2 text-sm text-gray-500 border-b">시장 국면 로딩...</div>;
	}
	if (error) {
		return <div className="px-4 py-2 text-sm text-red-600 border-b">국면 조회 실패: {error}</div>;
	}
	if (!data || !data.current) return null;

	const c = data.current;
	const colors = regimeColors[c.regime];

	return (
		<div className={`px-4 py-3 border-b border-l-4 ${colors.bg} ${colors.border}`}>
			<div className="flex items-center gap-6 flex-wrap">
				<div className="min-w-[180px]">
					<div className="text-xs text-gray-500">시장 국면 ({c.baseDate})</div>
					<div className={`text-lg font-bold ${colors.text}`}>{c.regimeLabel}</div>
					<div className="text-xs text-gray-600 mt-0.5">{regimeAdvice[c.regime]}</div>
				</div>

				<Metric label="인덱스" value={fmt(c.indexValue)} />
				<Metric
					label="SMA200 이격도"
					value={fmt(c.deviationPct, "%")}
					color={colorForDev(c.deviationPct)}
				/>
				<Metric label="SMA200" value={fmt(c.sma200)} />
				<Metric
					label="20일 변동성 (연환산)"
					value={fmt(c.vol20Pct, "%")}
					color={colorForVol(c.vol20Pct)}
				/>

				<button
					onClick={() => setShowHistory((v) => !v)}
					className="ml-auto text-sm text-gray-700 underline hover:text-blue-600"
				>
					{showHistory ? "추이 닫기" : "최근 30일 추이"}
				</button>
			</div>

			{showHistory && (
				<div className="mt-3 max-h-64 overflow-auto bg-white rounded border border-gray-200">
					<table className="text-xs w-full">
						<thead className="bg-gray-100 sticky top-0">
							<tr className="text-gray-600">
								<th className="px-2 py-1 text-left">일자</th>
								<th className="px-2 py-1 text-right">인덱스</th>
								<th className="px-2 py-1 text-right">이격도</th>
								<th className="px-2 py-1 text-right">변동성</th>
								<th className="px-2 py-1 text-left">국면</th>
							</tr>
						</thead>
						<tbody>
							{[...data.history].reverse().map((s) => (
								<tr key={s.baseDate} className="border-t border-gray-100">
									<td className="px-2 py-1">{s.baseDate}</td>
									<td className="px-2 py-1 text-right">{fmt(s.indexValue)}</td>
									<td className={`px-2 py-1 text-right ${colorForDev(s.deviationPct)}`}>
										{fmt(s.deviationPct, "%")}
									</td>
									<td className={`px-2 py-1 text-right ${colorForVol(s.vol20Pct)}`}>
										{fmt(s.vol20Pct, "%")}
									</td>
									<td className={`px-2 py-1 ${regimeColors[s.regime].text}`}>
										{s.regimeLabel}
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			)}
		</div>
	);
}

function Metric({ label, value, color = "" }: { label: string; value: string; color?: string }) {
	return (
		<div>
			<div className="text-xs text-gray-500">{label}</div>
			<div className={`text-base font-semibold ${color}`}>{value}</div>
		</div>
	);
}
