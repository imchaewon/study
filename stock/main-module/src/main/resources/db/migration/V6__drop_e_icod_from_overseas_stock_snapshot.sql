-- 업종 정보를 nasdaq_universe.industry로 일원화. snapshot의 e_icod 컬럼은 더 이상 사용 안 함.
ALTER TABLE overseas_stock_snapshot DROP COLUMN e_icod;
