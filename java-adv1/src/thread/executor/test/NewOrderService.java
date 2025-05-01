package thread.executor.test;

import java.util.List;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class NewOrderService {
	public void order(String orderNo) throws InterruptedException {
		InventoryWork inventoryWork = new InventoryWork(orderNo);
		ShippingWork shippingWork = new ShippingWork(orderNo);
		AccountingWork accountingWork = new AccountingWork(orderNo);

		// 작업 요청
		ExecutorService es = Executors.newFixedThreadPool(10);
		List<Future<Boolean>> futures = es.invokeAll(List.of(inventoryWork, shippingWork, accountingWork));
		es.shutdown();

		boolean allSuccess = futures.stream()
			.allMatch(e -> {
				try {
					return e.get();
				} catch (InterruptedException | ExecutionException ex) {
					return false;
				}
			});

		// 결과 확인
		if (allSuccess) {
			log("모든 주문 처리가 성공적으로 완료되었습니다.");
		} else {
			log("일부 작업이 실패했습니다");
		}
	}

	static class InventoryWork implements Callable<Boolean> {
		private final String orderNo;

		InventoryWork(String orderNo) {
			this.orderNo = orderNo;
		}

		@Override
		public Boolean call() {
			log("재고 업데이트: " + orderNo);
			sleep(1000);
			return true;
		}
	}

	static class ShippingWork implements Callable<Boolean> {
		private final String orderNo;

		ShippingWork(String orderNo) {
			this.orderNo = orderNo;
		}

		@Override
		public Boolean call() {
			log("배송 시스템 알림: " + orderNo);
			sleep(1000);
			return true;
		}
	}

	static class AccountingWork implements Callable<Boolean> {
		private final String orderNo;

		AccountingWork(String orderNo) {
			this.orderNo = orderNo;
		}

		@Override
		public Boolean call() {
			log("회계 시스템 업데이트: " + orderNo);
			sleep(1000);
			return true;
		}
	}

}