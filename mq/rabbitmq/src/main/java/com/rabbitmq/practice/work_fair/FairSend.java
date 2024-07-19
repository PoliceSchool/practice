package com.rabbitmq.practice.work_fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FairSend {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取连接
        Connection con = ConnectionUtils.getConnection();
        // 创建频道
        Channel channel = con.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /**
         * 每个消费者发送确认消息之前,消息队列不发送下一个消息到消费者,一次只处理一个消息
         * 限制发送给同一个消费者不得超过一条消息
         */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        for (int i = 0; i < 50; i++) {
            String msg = "hello " + i;
            System.out.println("[WQ] send:" + msg);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(i * 5);
        }
        channel.close();
        con.close();
    }
}
