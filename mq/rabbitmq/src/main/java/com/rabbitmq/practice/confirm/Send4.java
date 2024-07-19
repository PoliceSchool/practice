package com.rabbitmq.practice.confirm;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * mq confirm模式下的异步确认模式，添加了属性的配置
 */
public class Send4 {
    private static final String QUEUE_NAME = "test_queue_confirm4";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection con = ConnectionUtils.getConnection();
        // 从连接中获取一个通道
        Channel channel = con.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect 将channel设置为confirm模式
        // 注意,如果该队列以及设置为tx模式,那么将其设置为confirm模式将会报错
        channel.confirmSelect();

        // 存放未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());

        // 通道添加监听
        channel.addConfirmListener(new ConfirmListener() {
            // 没有问题的回执
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                // multiple:有可能一下子返回多个消息的确认,所以需要这个字段
                if (multiple) {
                    System.out.println("----handleAck----multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("----handleAck----multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }

            // 有问题的回执,具体业务逻辑具体处理,你可以重发,但是这里就直接删除了
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("----handleNack----multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("----handleNack----multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });

        String msg = "hello confirm message add confirm listener!";
        int i = 0;
        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().messageId("" + i++).build();
            channel.basicPublish("", QUEUE_NAME, properties, msg.getBytes());
            confirmSet.add(seqNo);
            Thread.sleep(500);
        }
    }
}
