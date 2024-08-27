package com.policeschool.code.gzqhero.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrderItem {
    private Long id;
    private Product product;
    private BigDecimal amount;
    private BigDecimal price;
    private Long orderId;
}
