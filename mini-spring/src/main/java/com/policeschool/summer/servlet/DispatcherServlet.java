package com.policeschool.summer.servlet;




import com.policeschool.summer.annotation.*;
import com.policeschool.summer.controller.HomeController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lujingxiao
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * 基包下面所有的带包路径权限定类名
     */
    private List<String> packageNames = new ArrayList<>();
    /**
     * 注解实例化，注解上的名称：实例化对象
     */
    private Map<String, Object> instanceMap = new HashMap<>();
    /**
     * 带包路径的全限定名称：注解上的名称
     */
    private Map<String, String> nameMap = new HashMap<>();
    /**
     * URL地址和方法的映射关系，springMvc就是方法调用链
     */
    private Map<String, Method> urlMethodMap = new HashMap<>();
    /**
     * Method和全限定类名映射关系，主要是为了通过Method找到该方法的对象利用反射执行
     */
    private Map<Method, String> methodPackageMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 扫描的基包
        final String basePackage = "com.java_practice_code.summer";
        try {
            // 扫描基包得到全部的带包全限定类名
            scanBasePackage(basePackage);
            // 把带@Controller、@Service、@Repository的类实例化放入map中，key为注解上的名称
            instance(packageNames);
            // springIOC注入
            springIOC();
            // 完成URL和方法的映射关系
            handlerUrlMethodMap();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void scanBasePackage(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        if (url == null) {
            return;
        }
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:" + basePackageFile);
        File[] childFiles = basePackageFile.listFiles();
        if (childFiles == null) {
            return;
        }
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                scanBasePackage(basePackage + "." + childFile.getName());
            } else if (childFile.isFile()) {
                packageNames.add(basePackage + "." + childFile.getName().split("\\.")[0]);
            }
        }
    }

    private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String packageName : packageNames) {
            Class c = Class.forName(packageName);
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();

                instanceMap.put(controllerName, c.newInstance());
                nameMap.put(packageName, controllerName);
                System.out.println("Controller : " + packageName + " , value : " + controller.value());
            } else if (c.isAnnotationPresent(Service.class)) {
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();

                instanceMap.put(serviceName, c.newInstance());
                nameMap.put(packageName, serviceName);
                System.out.println("Service : " + packageName + " , value : " + service.value());
            } else if (c.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository) c.getAnnotation(Repository.class);
                String repositoryName = repository.value();

                instanceMap.put(repositoryName, c.newInstance());
                nameMap.put(packageName, repositoryName);
                System.out.println("Repository : " + packageName + " , value : " + repository.value());
            }
        }
    }

    private void springIOC() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    String name = field.getAnnotation(Autowired.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), instanceMap.get(name));
                }
            }
        }
    }

    private void handlerUrlMethodMap() throws ClassNotFoundException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String packageName : packageNames) {
            Class c = Class.forName(packageName);
            if (c.isAnnotationPresent(Controller.class)) {
                Method[] methods = c.getMethods();
                StringBuilder baseUrl = new StringBuilder();
                if (c.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        baseUrl.append(requestMapping.value());

                        urlMethodMap.put(baseUrl.toString(), method);
                        methodPackageMap.put(method, packageName);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replaceAll(contextPath, "");

        // 通过path找到Method
        Method method = urlMethodMap.get(path);
        if (method != null) {
            String packageName = methodPackageMap.get(method);
            String controllerName = nameMap.get(packageName);

            HomeController homeController = (HomeController) instanceMap.get(controllerName);
            try {
                method.setAccessible(true);
                method.invoke(homeController);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
