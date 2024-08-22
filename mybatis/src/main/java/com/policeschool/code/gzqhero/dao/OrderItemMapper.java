package com.policeschool.code.gzqhero.dao;

import com.policeschool.code.gzqhero.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    OrderItem find(Long id);

    List<OrderItem> findByOrderId(Long orderId);

    int save(@Param("orderItem") OrderItem orderItem, @Param("orderId") Long orderId);
}
