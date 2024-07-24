package com.rocketmq.practice.transaction.localmsgtable.order;

import com.google.gson.Gson;
import com.rocketmq.practice.transaction.ICBCTransactionListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    private static final TransactionMQProducer producer = new TransactionMQProducer("tpg");
    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2000), r -> {
        Thread t = new Thread(r);
        t.setName("client-transaction-msg-check-thread");
        return t;
    });

    static {
        producer.setNamesrvAddr("192.168.3.10:9876");
        producer.setExecutorService(executorService);
        producer.setTransactionListener(new ICBCTransactionListener());
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void send() throws MQClientException {
        String tag = "OrderCreate";
        Order order = new Order();
        Random random = new Random();
        order.setItemId(String.valueOf(random.nextLong()));
        order.setOrderNo(String.valueOf(random.nextLong()));
        order.setOrderStatus("created");
        Message msg = new Message("TransactionTopic", tag, new Gson().toJson(order).getBytes());
        msg.setKeys(order.getOrderNo());
        TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, null);
        System.out.println("发送结果为：" + sendResult.getSendStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Order order) {
        System.out.println("保存订单到数据库中，{}" + order);
        // todo 写到本地消息表
    }

    public void checkOrderTransaction(Order order) {

    }
}
