package com.policeschool.code;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public class DaoUtils {
    private static SqlSessionFactory factory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.err.println("read mybatis-config.xml fail");
            e.printStackTrace();
            System.exit(1);
        }
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static <R> R execute(Function<SqlSession, R> function) {
        SqlSession session = factory.openSession();
        try {
            R apply = function.apply(session);
            session.commit();
            return apply;
        } catch (Throwable t) {
            session.rollback();
            System.out.println("execute error");
            throw t;
        } finally {
            session.close();
        }
    }
}
