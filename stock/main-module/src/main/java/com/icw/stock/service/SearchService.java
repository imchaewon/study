package com.icw.stock.service;

import com.icw.stock.model.api.StockSimplePrice;
import com.icw.stock.repository.StockDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
	private final StockDetailRepository stockDetailRepository;

	public StockSimplePrice[] getSimplePrice(String[] arr){
		List<StockSimplePrice> list = stockDetailRepository.findCustomColumns();

		StockSimplePrice[] result = new StockSimplePrice[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			StockSimplePrice match = list.stream().filter(e -> e.getCode().equals(s)).findAny().get();
			result[i] = match;
		}

		return result;
	}
}
