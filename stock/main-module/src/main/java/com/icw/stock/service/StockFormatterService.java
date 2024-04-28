package com.icw.stock.service;

import com.icw.stock.model.stock.req.StockReqDTO;

public interface StockFormatterService {
	public String changeToStr(String reqTxt);

	public String changeToStr(String reqTxt, String date);

	public StockReqDTO changeToDTO(String reqTxt);

	public StockReqDTO changeToDTO(String reqTxt, String date);
}