package com.policeschool.summer.web;

import com.policeschool.summer.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ljx
 */
public class TomcatServer {
    /**
     * 自带一个tomcat实例
     */
    private Tomcat tomcat;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    private TomcatServer() {
    }

    private void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        //设置tomcat在操作系统上监听的端口号
        tomcat.setPort(6699);
        tomcat.start();//启动tomcat

        //tomcat中的容器是分层级的，存放servlet的是context容器，先初始化一个
        Context context = new StandardContext();
        //设置context的路径为空
        context.setPath("/summer");
        //注册一个监听器
        context.addLifecycleListener(new Tomcat.FixContextListener());

        //创建DispatcherServlet对象，所有请求都先经过此处
        DispatcherServlet servlet = new DispatcherServlet();
        //把dispatcherServlet注册进Tomcat中，并设置为异步
        Tomcat.addServlet(context, "dispatcherServlet", servlet).setAsyncSupported(true);
        //并为DispatcherServlet设置请求路径，设置为根目录意味着所有请求都会到这里
        context.addServletMappingDecoded("/*", "dispatcherServlet");
        //把context注册到host中，host是tomcat中更高一级的容器
        tomcat.getHost().addChild(context);
        //设置tomcat线程一直等待，不然的话启动完就会关闭
        executorService.execute(() -> TomcatServer.this.tomcat.getServer().await());
    }

    public static void main(String[] args) {
        System.out.println("Hello Summer!");
        TomcatServer tomcatServer = new TomcatServer();
        try {
            //启动tomcat
            tomcatServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
