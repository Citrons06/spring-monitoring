package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed("my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
            log.info("주문");
            stock.decrementAndGet();
            sleep(500); //500~700
    }

    private static void sleep(int l) {
        try {
            Thread.sleep(l + new Random().nextInt(200));  //0~200 사이의 랜덤 지연 시간 추가
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancel() {
            log.info("취소");
            stock.incrementAndGet();
            sleep(200);  //200~400
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
