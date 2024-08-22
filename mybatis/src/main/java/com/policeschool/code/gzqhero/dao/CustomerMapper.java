package com.policeschool.code.gzqhero.dao;

import com.policeschool.code.gzqhero.domain.Customer;

public interface CustomerMapper {
    Customer find(Long id);

    Customer findWithAddress(Long id);

    Customer findByOrderId(Long orderId);

    int save(Customer customer);
}
