-- 해외주식 스냅샷 테이블 (기존 overseas_stock_yyyyMMdd.txt 파일 저장 데이터)
CREATE TABLE IF NOT EXISTS overseas_stock_snapshot (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    base_date   VARCHAR(8)   NOT NULL COMMENT '수집일자 yyyyMMdd',
    code        VARCHAR(50)  NOT NULL COMMENT '종목코드',
    base        DOUBLE       NULL COMMENT '현재가',
    l52p        DOUBLE       NULL COMMENT '52주 최저가',
    h52p        DOUBLE       NULL COMMENT '52주 최고가',
    pvol        BIGINT       NULL COMMENT '전일거래량',
    tvol        BIGINT       NULL COMMENT '거래량',
    tamt        BIGINT       NULL COMMENT '거래대금',
    e_icod      VARCHAR(200) NULL COMMENT '업종(한글)',
    ordyn       VARCHAR(50)  NULL COMMENT '매매가능여부',
    PRIMARY KEY (id),
    INDEX idx_overseas_stock_snapshot_base_date (base_date),
    INDEX idx_overseas_stock_snapshot_base_date_code (base_date, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='해외주식 일별 스냅샷';
