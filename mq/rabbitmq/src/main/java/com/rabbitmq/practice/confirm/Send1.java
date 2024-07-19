package com.rabbitmq.practice.confirm;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * mq confirm模式下的普通模式,即每发送一条消息就确认一条消息
 */
public class Send1 {
    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect 将channel设置为confirm模式
        // 注意,如果该队列以及设置为tx模式,那么将其设置为confirm模式将会报错
        channel.confirmSelect();

        String msg = "hello confirm message!";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        if (!channel.waitForConfirms()) {
            System.out.println("send failed");
        } else {
            System.out.println("send ok");
        }

        channel.close();
        con.close();
    }
}
