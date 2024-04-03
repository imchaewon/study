package com.icw.stock.service;

import com.icw.stock.model.stock.req.api.domestic.StockSimplePriceApiReqDTO;
import com.icw.stock.repository.domestic.StockDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DomesticStockService {
	private final StockDetailRepository stockDetailRepository;

	public StockSimplePriceApiReqDTO[] getSimplePrice(String[] arr){
		List<StockSimplePriceApiReqDTO> list = stockDetailRepository.findCustomColumns();

		StockSimplePriceApiReqDTO[] result = new StockSimplePriceApiReqDTO[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			StockSimplePriceApiReqDTO match = list.stream().filter(e -> e.getCode().equals(s)).findAny().get();
			result[i] = match;
		}

		return result;
	}
}
