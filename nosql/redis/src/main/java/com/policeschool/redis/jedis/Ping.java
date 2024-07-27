package com.policeschool.redis.jedis;

import redis.clients.jedis.Jedis;

public class Ping {

    public static void main(String[] args) {
        // 创建jedis对象
        Jedis jedis = new Jedis("192.168.3.130", 6379);
        // 设置密码，在redis.conf里面的requirepass里面设置
        jedis.auth("123456");
        // 测试
        String ping = jedis.ping();
        System.out.println(ping);
    }


}
