package com.policeschool.code.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Product product;
    private BigDecimal amount;
    private BigDecimal price;
    private Long orderId;
}
