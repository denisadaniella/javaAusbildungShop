package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.LocationOrderDto;
import ro.msg.learning.shop.exception.SalesRevenueException;
import ro.msg.learning.shop.model.Revenue;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.RevenueRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SalesRevenueService {

    private final OrderRepository orderRepository;
    private final RevenueRepository revenueRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public void aggregateSalesRevenue(LocalDate date) {

        LocalDateTime localDateTimeBegin = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime localDateTimeEnd = LocalDateTime.of(date, LocalTime.MAX);

        List<LocationOrderDto> locationOrderDtos = orderRepository.aggregateSalesByDate(localDateTimeBegin, localDateTimeEnd);

        if (locationOrderDtos.isEmpty()) {
            throw new SalesRevenueException(date);
        }

        locationOrderDtos.forEach(p -> {

            Revenue revenue = revenueRepository.findByLocationIdAndDate(p.getLocationId(), date);

            if (revenue == null) {
                revenue = new Revenue(locationRepository.findOne(p.getLocationId()), date, p.getSum());
            } else if (!revenue.getSum().equals(p.getSum())) {
                revenue.setSum(p.getSum());
            } else {
                return;
            }

            revenueRepository.save(revenue);
        });

    }
}
