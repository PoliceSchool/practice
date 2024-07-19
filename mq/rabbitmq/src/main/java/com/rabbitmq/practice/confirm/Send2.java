package com.rabbitmq.practice.confirm;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * mq confirm模式下的批量模式,即发送一批消息就确认一批消息
 * 值得注意的是:如果出问题了那么这一批消息都消失了,需要重复发送这一批消息;有可能性能反而下降
 *
 * 批量模式也简单,就是将普通模式的发送单条改为for循环发送多条
 * 最后再统一确认即可
 */
public class Send2 {
    private static final String QUEUE_NAME = "test_queue_confirm2";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect 将channel设置为confirm模式
        // 注意,如果该队列以及设置为tx模式,那么将其设置为confirm模式将会报错
        channel.confirmSelect();

        String msg = "hello confirm message batch!";
        // 批量发送
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }
        // 批量确认
        if (!channel.waitForConfirms()) {
            System.out.println("send failed");
        } else {
            System.out.println("send ok");
        }

        channel.close();
        con.close();
    }
}
