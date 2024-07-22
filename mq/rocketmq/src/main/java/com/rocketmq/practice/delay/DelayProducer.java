package com.rocketmq.practice.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DelayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("192.168.3.129:9876;192.168.3.130:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("DelayTopic", "DelayTag", body);
            // 指定消息延迟等级为3级，即延迟10秒
            msg.setDelayTimeLevel(3);
            SendResult send = producer.send(msg);
            System.out.print(new SimpleDateFormat("mm:ss").format(new Date()));
            System.out.println(" ," + send);
        }
        producer.shutdown();
    }
}
