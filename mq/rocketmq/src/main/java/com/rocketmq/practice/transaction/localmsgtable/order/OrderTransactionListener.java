package com.rocketmq.practice.transaction.localmsgtable.order;

import com.google.gson.Gson;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderTransactionListener implements TransactionListener {
    @Resource
    private OrderService orderService;

    // 回调操作
    // 消息预提交成功就会出发该方法的执行，用于完成本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
        System.out.println("预提交消息成功：" + message);
        // 本地消息表去重
        Order order = new Gson().fromJson(new String(message.getBody()), Order.class);
        order.setTransactionId(message.getTransactionId());
        orderService.save(order);
        return LocalTransactionState.COMMIT_MESSAGE;
    }


    // 消息回查方法
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println("执行消息回查：" + messageExt.getTags());
        // todo 查询本地事务表该事务是否执行成功
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
