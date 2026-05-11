-- 기존 overseas_stock_yyyyMMdd.txt 형식에 맞게 e_icod(업종 한글), ordyn(매매가능여부) 길이 확장
ALTER TABLE overseas_stock_snapshot
    MODIFY e_icod VARCHAR(200) NULL COMMENT '업종(한글)',
    MODIFY ordyn  VARCHAR(50)  NULL COMMENT '매매가능여부';
