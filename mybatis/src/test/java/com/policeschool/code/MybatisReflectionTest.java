package com.policeschool.code;

import com.policeschool.code.domain.Customer;
import org.apache.ibatis.reflection.*;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.junit.Test;

/**
 * 对Mybatis反射模块的使用测试
 */
public class MybatisReflectionTest {

    @Test
    public void test01() {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        Reflector reflector = reflectorFactory.findForClass(Customer.class);
        String[] getablePropertyNames = reflector.getGetablePropertyNames();
        for (String getablePropertyName : getablePropertyNames) {
            System.out.println("getAble Property:" + getablePropertyName);
        }

        Class<?> getterType = reflector.getGetterType("phone");
        System.out.println("Getter Type Of myProperty:" + getterType);

        MetaClass metaClass = MetaClass.forClass(Customer.class, reflectorFactory);
        boolean phone = metaClass.hasGetter("phone");
        System.out.println("Has Getter for phone: " + phone);

        // 使用 MetaObject
        Customer myObject = new Customer();
        ObjectFactory objectFactory = new DefaultObjectFactory();
        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        MetaObject metaObject = MetaObject.forObject(myObject, objectFactory, objectWrapperFactory, reflectorFactory);

        // 设置属性值
        metaObject.setValue("phone", "123456789");

        // 获取属性值
        Object value = metaObject.getValue("phone");
        System.out.println("Value of phone: " + value);
    }

}
