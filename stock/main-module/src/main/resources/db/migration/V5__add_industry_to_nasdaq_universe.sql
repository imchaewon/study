-- universe 갱신 시 KIS price-detail(HHDFS76200200) 응답의 e_icod(업종 한글)를 함께 저장.
ALTER TABLE nasdaq_universe
    ADD COLUMN industry VARCHAR(200) NULL COMMENT '업종(한글, KIS API e_icod)' AFTER rank_no;
