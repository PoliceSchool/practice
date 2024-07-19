package com.rabbitmq.practice.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.concurrent.TimeoutException;

public class TxSend {
    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "hello tx message!";
        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//            int xx = 1 / 0;
            System.out.println("send msg");
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("send message tx rollback");
        } finally {
            channel.close();
            con.close();
        }
    }
}
