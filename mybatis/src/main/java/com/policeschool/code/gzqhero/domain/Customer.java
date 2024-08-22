package com.policeschool.code.gzqhero.domain;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private Long id;
    private String name;
    private String phone;
    private List<Address> addresses;
}
