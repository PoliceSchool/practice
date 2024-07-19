package com.rabbitmq.practice.tx;

import com.rabbitmq.client.*;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TxRecv {
    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            // 获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgString = new String(body, "utf-8");
                System.out.println("recv[tx] msg:" + msgString);
            }
        });
    }
}
