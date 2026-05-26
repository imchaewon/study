-- 매주 갱신되는 NASDAQ 시총 상위 종목 universe.
-- 매일 돌아가는 해외주식 수집 배치가 이 테이블을 읽어 대상 종목을 결정한다.
CREATE TABLE IF NOT EXISTS nasdaq_universe (
    ticker        VARCHAR(20)  NOT NULL COMMENT '종목 심볼',
    exchange      VARCHAR(10)  NOT NULL DEFAULT 'NAS' COMMENT '거래소 코드 (KIS 형식)',
    company_name  VARCHAR(255) NULL COMMENT '회사명',
    market_cap    BIGINT       NULL COMMENT '시총 (USD)',
    rank_no       INT          NOT NULL COMMENT '시총 순위 (1=가장 큼)',
    updated_at    DATETIME     NOT NULL COMMENT '레코드 최종 갱신 시각',
    PRIMARY KEY (ticker),
    INDEX idx_nasdaq_universe_rank (rank_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='NASDAQ 시총 상위 종목 universe';
