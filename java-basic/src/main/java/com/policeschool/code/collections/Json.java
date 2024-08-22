package com.policeschool.code.collections;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;


public class Json {
    public static void main(String[] args) {
        String str = "{\"custfield_login_appBuId\": 2065}";
        JSONObject extJson = new JSONObject();
        if (CharSequenceUtil.isNotEmpty(str)) {
            extJson = JSON.parseObject(str);
        }
        String loginAppBuId = extJson.getString("custfield_login_appBuId");
        JSONArray jsonArray = JSONUtil.parseArray(loginAppBuId);
        jsonArray.add(9999L);
        extJson.put("custfield_login_appBuId", JSONUtil.toJsonStr(jsonArray));
        System.out.println(extJson);
    }

    public static void method1() {
        MyClass obj = new MyClass(5, "asd");
        List<MyClass> objList = new ArrayList<>();
        objList.add(obj);

        List<MyClass> copyList = BeanUtil.copyToList(objList, MyClass.class);
        obj.age = 8;
        obj.name = "ddd";
        System.out.println(objList.get(0));
        System.out.println(copyList.get(0));
    }

    static class MyClass {
        public int age;
        public String name;

        public MyClass() {
        }

        public MyClass(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyClass{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
