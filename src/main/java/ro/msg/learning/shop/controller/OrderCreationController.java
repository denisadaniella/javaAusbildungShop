package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderCreationService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/create/order")
@AllArgsConstructor
public class OrderCreationController {

    private final OrderCreationService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Order createOrder(@RequestBody OrderCreationDto orderCreationDto) {
        orderCreationDto.setLocalDateTime(LocalDateTime.now());
        return service.createOrder(orderCreationDto);

    }

}
