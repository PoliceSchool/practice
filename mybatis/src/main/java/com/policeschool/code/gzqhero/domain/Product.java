package com.policeschool.code.gzqhero.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
