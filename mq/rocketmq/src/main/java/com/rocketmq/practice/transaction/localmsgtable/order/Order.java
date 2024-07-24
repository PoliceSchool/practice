package com.rocketmq.practice.transaction.localmsgtable.order;

import lombok.Data;

@Data
public class Order {
    private String itemId;
    private String orderNo;
    private String orderStatus;
    private String transactionId;
}
