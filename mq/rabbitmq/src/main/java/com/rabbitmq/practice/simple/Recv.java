package com.rabbitmq.practice.simple;

import com.rabbitmq.client.*;
import com.rabbitmq.practice.util.ConnectionUtils;

import javax.print.attribute.standard.QueuedJobCount;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        newApi();
    }

    private static void newApi() throws IOException, TimeoutException {
        // 获取连接
        Connection con = ConnectionUtils.getConnection();
        // 创建频道
        Channel channel = con.createChannel();
        // 队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgString = new String(body, "utf-8");
                System.out.println("new api recv:" + msgString);
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static void oldApi() throws IOException, InterruptedException, TimeoutException {
        // 获取连接
        Connection con = ConnectionUtils.getConnection();
        // 创建频道
        Channel channel = con.createChannel();
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery deliver = consumer.nextDelivery();
            String msgStrin = new String(deliver.getBody());
            System.out.println("{recv} msg:" + msgStrin);
        }
    }
}
