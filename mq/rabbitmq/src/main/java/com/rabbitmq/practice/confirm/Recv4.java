package com.rabbitmq.practice.confirm;

import com.rabbitmq.client.*;
import com.rabbitmq.practice.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息去重的方法应该在消费端处理： 发送端发送消息的时候应该带上一个correlationId，这个correlationId标识这个消息的编号，而且这个
 * correlationId应该在集群里面是唯一的，也即是分布式ID
 * 然后消费端接收到这个消息的时候先拿到correlationId，去缓存或者数据库里面查有没有这个消息的id，没有的话就继续处理业务逻辑，最后把
 * correlationId存进缓存或者数据库，然后ack给rabbitmq；下次再有相同的correlationId的话，由于数据库或者缓存有这个correlationId了，就不会继续操作了。
 *
 * 或者说消息的存储和业务处理可以分开处理，也即是监听程序得到一个消息，先拿到correlationId，判断数据库或者缓存有没有这个correlationId；
 * 没有则保存然后返回ack给rabbitmq，有的话不保存直接返回ack给mq；同时还有另外的线程从数据库或者缓存中拿消息去处理；这样的话可以加快
 * ack的应答时间，否则ack应答还要等到业务处理完才返回就有点慢了，降低rabbitmq的吞吐量。
 * 参考文章：https://itindex.net/detail/59929-rabbitmq-%E6%B6%88%E6%81%AF
 */
public class Recv4 {
    private static final String QUEUE_NAME = "test_queue_confirm4";

    public static void main(String[] args) throws IOException, TimeoutException {
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
                System.out.println("recv[confirm]:" + msgString);
                System.out.println("messageId:" + properties.getMessageId() + ", correlationId:" + properties.getCorrelationId());
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
