package com.icw.stock.batch;

import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import java.util.List;

public class TickerListItemReader implements ItemStreamReader<ExcdAndSymbDTO> {
	private static final String INDEX_KEY = "overseasStock.reader.index";

	private final List<ExcdAndSymbDTO> tickers;
	private int currentIndex = 0;

	public TickerListItemReader(List<ExcdAndSymbDTO> tickers) {
		this.tickers = tickers;
	}

	@Override
	public ExcdAndSymbDTO read() {
		if (currentIndex >= tickers.size()) {
			return null;
		}
		return tickers.get(currentIndex++);
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (executionContext.containsKey(INDEX_KEY)) {
			currentIndex = executionContext.getInt(INDEX_KEY);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		executionContext.putInt(INDEX_KEY, currentIndex);
	}

	@Override
	public void close() throws ItemStreamException {
		// no-op
	}
}
