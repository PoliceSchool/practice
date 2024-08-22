package com.policeschool.code.gzqhero.dao;

import com.policeschool.code.gzqhero.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    Order find(Long id);

    List<Order> findByCustomerId(@Param("id") Long customerId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

    int save(Order order);
}
