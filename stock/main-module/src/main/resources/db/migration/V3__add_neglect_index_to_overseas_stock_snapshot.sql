-- 소외지수 = (현재가 - 52주저점) / (52주고점 - 52주저점) * 100
-- 입력값이 모두 raw로 저장되어 있어 STORED Generated Column으로 자동 산출
ALTER TABLE overseas_stock_snapshot
    ADD COLUMN neglect_index DOUBLE
        GENERATED ALWAYS AS (
            CASE
                WHEN base IS NULL OR l52p IS NULL OR h52p IS NULL THEN NULL
                WHEN h52p <= l52p THEN NULL
                ELSE (base - l52p) / (h52p - l52p) * 100
            END
        ) STORED
        COMMENT '소외지수 0~100, 100=52주고점 근처';

CREATE INDEX idx_overseas_stock_snapshot_base_date_neglect
    ON overseas_stock_snapshot (base_date, neglect_index);
