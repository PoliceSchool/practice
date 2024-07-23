package com.rocketmq.practice.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RocketMQ的事务消息保证最终一致性，因为RocketMQ的事务消息指的是发送方与Broker的事务一致性
 * RocketMQ只能保证发送到的消息能够正确存储到Broker
 * 然后RocketMQ再将这个消息发送给消费者，消费者要保证这个消息能被正确的消费了，至于消费者能不能保证那就是消费者的事情了
 * 所以在消息被消费者正确消费之前，上游系统的状态跟下游系统的状态是不一致的
 * 但是如果下游系统正确的消费了消息，那么就能保证事务的最终一致性
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("tpg");
        producer.setNamesrvAddr("192.168.3.129:9876;192.168.3.130:9876");

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000), r -> {
            Thread t = new Thread(r);
            t.setName("client-transaction-msg-check-thread");
            return t;
        });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(new ICBCTransactionListener());

        producer.start();

        String[] tags = {"SuccessTag", "FailTag", "UnkownTag"};
        for (int i = 0; i < 3; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TransactionTopic", tags[i], body);
            msg.setKeys("transaction-key" + i);
            TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, null);
            System.out.println("发送结果为：" + sendResult.getSendStatus());
        }
    }
}
