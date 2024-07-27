package com.policeschool.code.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Customer customer;
    private Address deliveryAddress;
    private List<OrderItem> orderItems;
    private ZonedDateTime createTime;
    private BigDecimal totalPrice;
}
