package com.policeschool.code.dao;

import com.policeschool.code.domain.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    Address find(Long id);

    List<Address> findAll(Long customerId);

    Address findByOrderId(Long orderId);

    int save(@Param("address") Address address, @Param("customerId") Long customerId);
}
