package com.rabbitmq.practice.work_fair;

import com.rabbitmq.client.*;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FairRecv2 {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取连接
        Connection con = ConnectionUtils.getConnection();
        // 创建频道
        Channel channel = con.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 保证一次只分发一个
        channel.basicQos(1);
        // 定义一个消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgString = new String(body, "utf-8");
                System.out.println("[2] Recv msg:" + msgString);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[2] done");
                    // 手动回执
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        boolean autoAck = false;  //自动应答改成false
        // 监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
