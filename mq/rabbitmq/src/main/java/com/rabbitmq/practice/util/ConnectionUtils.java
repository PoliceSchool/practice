package com.rabbitmq.practice.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 获取MQ的连接
 */
public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("localhost");
        // AMQP 5672
        factory.setPort(5672);
        // vhost
        factory.setVirtualHost("/vhost_mmr");
        // 用户名
        factory.setUsername("user_mmr");
        // 密码
        factory.setPassword("user_mmr");
        return factory.newConnection();
    }

}
