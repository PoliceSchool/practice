package com.policeschool.code.dao;

import com.policeschool.code.domain.Customer;

public interface CustomerMapper {
    Customer find(Long id);

    Customer findWithAddress(Long id);

    Customer findByOrderId(Long orderId);

    int save(Customer customer);
}
