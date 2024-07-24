package com.rocketmq.practice.transaction.localmsgtable;

import com.rocketmq.practice.transaction.localmsgtable.order.OrderService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping(value = "/save")
    public String save() throws MQClientException {
        orderService.send();
        return "ssss";
    }
}
