package com.rocketmq.practice.general;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 异步发送消息
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("192.168.3.129:9876;192.168.3.130:9876");
        producer.setRetryTimesWhenSendAsyncFailed(3);
        producer.setSendMsgTimeout(5000);
        producer.start();

        for (int i = 0; i < 100; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("AsynDeerTopic", "asyn-tag", body);
            msg.setKeys("asyn-key" + i);
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(3);
        producer.shutdown();
    }
}
