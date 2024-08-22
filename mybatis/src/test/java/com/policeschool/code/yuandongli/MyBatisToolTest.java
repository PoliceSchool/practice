package com.policeschool.code.yuandongli;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * MyBatis中常用的工具类的使用测试
 */
public class MyBatisToolTest {

    /**
     * XPathParse用来解析MyBatis中的各种xml文件，从而将xml文件中的各种属性和属性值解析出来
     *
     * @throws IOException IO异常
     */
    @Test
    public void testXPathParse() throws IOException {
        Resource resource = new ClassPathResource("mybatis-config.xml");
        XPathParser xPathParser = new XPathParser(resource.getInputStream(), true, null, new XMLMapperEntityResolver());
        XNode xNode = xPathParser.evalNode("/configuration/properties");
        System.out.println(xNode);
    }


    @Test
    public void testConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put("driver", "com.mysql.jdbc.Driver");
        properties.put("url", "jdbc:mysql://192.168.3.130:3306/test_mybatis");
        properties.put("username", "root");
        properties.put("password", "root");
        configuration.setVariables(properties);
        // 添加驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
        // 配置类的别名
        configuration.getTypeAliasRegistry().registerAlias("Customer", "com.policeschool.code.gzqhero.domain.Customer");
        // 配置环境
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(configuration.getVariables().getProperty("driver"));
        dataSource.setUrl(configuration.getVariables().getProperty("url"));
        dataSource.setUsername(configuration.getVariables().getProperty("username"));
        dataSource.setPassword(configuration.getVariables().getProperty("password"));
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        configuration.setEnvironment(environment);
        // 添加Mapper类，值得一提的是MyBatis扫描配置文件之后就是把每个Sql语句以下面的方式封装成MappedStatement然后添加到configuration里面
        SqlSource sqlSource = new StaticSqlSource(configuration, "insert into account (username, money) values ('jerry', 1000)");
        MappedStatement mappedStatement = new MappedStatement.Builder(configuration, "account.insert", sqlSource, SqlCommandType.INSERT).build();
        configuration.addMappedStatement(mappedStatement);

        System.out.println(configuration);
    }
}
