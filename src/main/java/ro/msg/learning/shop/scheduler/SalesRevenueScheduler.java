package ro.msg.learning.shop.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.service.SalesRevenueService;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class SalesRevenueScheduler {

    private final SalesRevenueService salesRevenueService;

    @Scheduled(cron = "${cron.expression}")
    public void run() {
        salesRevenueService.aggregateSalesRevenue(LocalDate.now());
    }


}
