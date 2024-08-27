package com.policeschool.code.yuandongli;

import com.policeschool.code.gzqhero.domain.OrderItem;
import com.policeschool.code.gzqhero.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.ognl.OgnlException;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.LongTypeHandler;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandler;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * MyBatis中常用的工具类的使用测试
 */
@Slf4j
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

    @Test
    public void testXMLConfigBuilder() throws IOException {
        InputStream inputStream = new ClassPathResource("mybatis-config.xml").getInputStream();
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        Configuration parse = xmlConfigBuilder.parse();
        log.info("parse: {}", parse);
    }

    @Test
    public void textOGNL() throws OgnlException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(1L);
        orderItem.setId(1L);
        orderItem.setAmount(BigDecimal.ONE);
        orderItem.setPrice(BigDecimal.TEN);
        orderItem.setProduct(new Product(1L, "洗发露", "desc", BigDecimal.TEN));

        OgnlContext context = new OgnlContext(null, null, new DefaultMemberAccess(false));
        Object value = Ognl.getValue("product.name", context, orderItem);
        log.info("product.name -> {}", value);

        Object res1 = Ognl.getValue("product.name == '洗发露'", context, orderItem);
        log.info("product.name == '洗发露' -> {}", res1);

        Object res2 = Ognl.getValue("product.name == null", context, orderItem);
        log.info("product.name == null -> {}", res2);

        // 方法调用
        context.put("orderItem", orderItem);
        Object res3 = Ognl.getValue("#orderItem.product.name.substring(1,2)", context, new Object());
        log.info("res3 -> {}", res3);
    }

    @Test
    public void testTypeAliasRegistry() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("orderItem", OrderItem.class);
        typeAliasRegistry.registerAliases("com.policeschool.code.gzqhero.domain");
    }

    @Test
    public void testTypeHandler() {
        Connection connection = null;
        String sql = "select * from t_product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TypeHandler<Long> typeHandler = new LongTypeHandler();
                Long id = typeHandler.getResult(resultSet, "id");
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMetaObject() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(1L);
        orderItem.setId(1L);
        orderItem.setAmount(BigDecimal.ONE);
        orderItem.setPrice(BigDecimal.TEN);
        orderItem.setProduct(new Product(1L, "洗发露", "desc", BigDecimal.TEN));
        log.info("orderItem: {}", orderItem);

        MetaObject metaObject = MetaObject.forObject(orderItem,
                new DefaultObjectFactory(), // 用来创建对象的工厂，可自定义然后配置在配置文件里面
                new DefaultObjectWrapperFactory(), // 用来包装对象的工厂，可自定义然后配置在配置文件里面
                new DefaultReflectorFactory()); // 用来反射对象的工厂，可自定义然后配置在配置文件里面
        metaObject.setValue("product.name", "洗洁精");
        log.info("orderItem: {}", orderItem);

        log.info("getValue: {}", metaObject.getValue("product.name"));
        // DefaultObjectFactory、DefaultObjectWrapperFactory、DefaultObjectWrapperFactory、Reflector、MetaClass
    }

    @Test
    public void testMappedStatement() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        Configuration configuration = xmlConfigBuilder.parse();

        StaticSqlSource sqlSource = new StaticSqlSource(configuration, "select id, name, description, price from t_product");

        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "select", sqlSource, SqlCommandType.SELECT);

        List<ResultMapping> resultMappings = new ArrayList<>();
        ResultMapping idMapping = new ResultMapping.Builder(configuration, "id", "id", Long.class).build();
        resultMappings.add(idMapping);
        ResultMapping nameMapping = new ResultMapping.Builder(configuration, "name", "name", String.class).build();
        resultMappings.add(nameMapping);
        ResultMapping descriptionMapping = new ResultMapping.Builder(configuration, "description", "description", String.class).build();
        resultMappings.add(descriptionMapping);
        ResultMapping priceMapping = new ResultMapping.Builder(configuration, "price", "price", BigDecimal.class).build();
        resultMappings.add(priceMapping);

        ResultMap resultMap = new ResultMap.Builder(configuration, "resultMap", Product.class, resultMappings).build();
        List<ResultMap> resultMaps = new ArrayList<>();
        resultMaps.add(resultMap);


        builder.resultMaps(resultMaps);
        builder.fetchSize(300);
        builder.databaseId("mysql");
        MappedStatement mappedStatement = builder.build();
        log.info("mappedStatement: {}", mappedStatement);
        BoundSql boundSql = mappedStatement.getBoundSql(new Product());
        log.info("sql -> {}", boundSql.getSql());

        // StaticSqlSource, DynamicSqlSource, SqlNode, DynamicContext, Ognl, IfSqlNode
        // WhereSqlNode, StaticTextSqlNode
    }

    @Test
    public void testXMLLanguageDriver() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-itnanlaoshi.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        Configuration configuration = xmlConfigBuilder.parse();

        InputStream sqlInputStream = Resources.getResourceAsStream("sql.xml");
        XPathParser xPathParser = new XPathParser(sqlInputStream);
        XNode xNode = xPathParser.evalNode("/select");

        XMLLanguageDriver xmlLanguageDriver = new XMLLanguageDriver();
        SqlSource sqlSource = xmlLanguageDriver.createSqlSource(configuration, xNode, Product.class);
        log.info("sqlSource --> {}", sqlSource);
    }

}
