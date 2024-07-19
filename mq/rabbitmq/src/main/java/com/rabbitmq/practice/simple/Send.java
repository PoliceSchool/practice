package com.rabbitmq.practice.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();
        // 创建队列,rabbitmq中的通信都是通过channel来的,仅仅有Connection还不行,一定要有channel
        // 一个Connection中可以有多个channel,因为TCP连接的建立和释放都是十分昂贵的;另外面对mq一秒成千上万次
        // 的请求,不可能建立那么多个Connection,服务器也没那么多连接可创建.Connection要公用,但是channel要分开使用
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "hello simple!";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("send msg");
        channel.close();
        con.close();
    }
}
