package com.rocketmq.practice.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 分区有序消息演示模板
 */
public class OrderedProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("192.168.3.129:9876;192.168.3.130:9876");
        // 若为全局有序消息则需要指定消息队列为1
        // producer.setDefaultTopicQueueNums(1);
        producer.start();

        for (int i = 0; i < 100; i++) {
            Integer orderId = i;
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("OrderedTopic", "OrderedTag", body);
            // 将orderId作为消息key
            msg.setKeys(orderId.toString());
            // send()的第三个参数值会传递给选择器的select()的第三个参数
            SendResult sendResult = producer.send(msg, (list, message, o) -> {
                // 以下是使用消息key作为选择的算法，消息key可以随着消息一起发送给客户端
                String keys = msg.getKeys();
                int id = Integer.parseInt(keys);

                // 以下是使用arg作为选择的算法，这个参数不会随着消息一起发送给客户端
                // Integer id = (Integer) o;


                int index = id % list.size();
                return list.get(index);
            }, orderId);

            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
