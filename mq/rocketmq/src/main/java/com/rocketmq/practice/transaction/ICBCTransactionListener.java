package com.rocketmq.practice.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class ICBCTransactionListener implements TransactionListener {

    // 回调操作
    // 消息预提交成功就会出发该方法的执行，用于完成本地食物
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
        System.out.println("预提交消息成功：" + message);
        // 假设接收到TagA的消息就表示扣款成功；TagB的消息表示扣款失败；TagC表示扣款不清楚需要消息回查
        if (StringUtils.equals("SuccessTag", message.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.equals("FailTag", message.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if (StringUtils.equals("UnkownTag", message.getTags())) {
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.UNKNOW;
    }


    // 消息回查方法
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println("执行消息回查：" + messageExt.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
