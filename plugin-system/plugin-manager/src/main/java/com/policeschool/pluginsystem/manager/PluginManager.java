package com.policeschool.pluginsystem.manager;

import com.policeschool.pluginsystem.api.MyPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PluginManager {

    private static MyClassLoader CLS_LOADER;

    private static final Map<String, MyPlugin> PLUGINS = new HashMap<>();

    public static MyPlugin loadPlugin(String jarPath, String className) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (!PLUGINS.containsKey(className)) {
            synchronized (PLUGINS) {
                if (!PLUGINS.containsKey(className)) {
                    URL[] urls = {new URL("file:" + jarPath)};
                    URLClassLoader classLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
                    Class<?> clazz = classLoader.loadClass(className);
                    PLUGINS.put(className, (MyPlugin) clazz.newInstance());
                }
            }
        }
        return PLUGINS.get(className);
    }

    public static void unloadPlugin(String className) {
        MyPlugin plugin = PLUGINS.remove(className);
        if (plugin != null) {
            plugin.stop();
        }
    }

    /**
     * 通过jar包的方式获取插件类
     */
    public static void loadByJar() {
        Map<String, String> jar2ClassMap = new HashMap<>();
        jar2ClassMap.put("C:\\Users\\jimson\\.m2\\repository\\com\\policeschool\\pluginsystem\\plugin-impl\\0.0.1-SNAPSHOT\\plugin-impl-0.0.1-SNAPSHOT.jar", "com.policeschool.pluginsystem.impl.MyPluginImpl");
        jar2ClassMap.put("C:\\Users\\jimson\\.m2\\repository\\com\\policeschool\\pluginsystem\\plugin-impl2\\0.0.1-SNAPSHOT\\plugin-impl2-0.0.1-SNAPSHOT.jar", "com.policeschool.pluginsystem.impl.MyPluginImpl2");
        jar2ClassMap.keySet().forEach(jarPath -> {
            MyPlugin plugin;
            try {
                plugin = loadPlugin(jarPath, jar2ClassMap.get(jarPath));
            } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("########################");
            System.out.println("类名:" + plugin);
            System.out.println("类加载器:" + plugin.getClass().getClassLoader());
            plugin.start();
            plugin.stop();
        });
    }

    public static MyPlugin loadByCustomeClassLoaderImpl(String jarPath, String className) throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (Objects.isNull(CLS_LOADER)) {
            synchronized (PluginManager.class) {
                if (Objects.isNull(CLS_LOADER)) {
                    CLS_LOADER = new MyClassLoader(jarPath);
                }
            }
        }
        if (!CLS_LOADER.containJarPath(jarPath)) {
            CLS_LOADER.addJarPath(jarPath);
        }
        return CLS_LOADER.loadPlugin(className);
    }

    public static void loadByCustomeClassLoader() {
        Map<String, String> jar2ClassMap = new HashMap<>();
        jar2ClassMap.put("C:\\Users\\jimson\\.m2\\repository\\com\\policeschool\\pluginsystem\\plugin-impl\\0.0.1-SNAPSHOT\\plugin-impl-0.0.1-SNAPSHOT.jar", "com.policeschool.pluginsystem.impl.MyPluginImpl");
        jar2ClassMap.put("C:\\Users\\jimson\\.m2\\repository\\com\\policeschool\\pluginsystem\\plugin-impl2\\0.0.1-SNAPSHOT\\plugin-impl2-0.0.1-SNAPSHOT.jar", "com.policeschool.pluginsystem.impl.MyPluginImpl2");
        jar2ClassMap.forEach((k, v) -> {
            MyPlugin plugin;
            try {
                plugin = loadByCustomeClassLoaderImpl(k, v);
            } catch (MalformedURLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("########################");
            System.out.println("类名:" + plugin);
            System.out.println("类加载器:" + plugin.getClass().getClassLoader());
            plugin.start();
            plugin.stop();
        });
    }

    public static void main(String[] args) throws InterruptedException {
//        loadByJar();
        loadByCustomeClassLoader();
        while(true){
            Thread.sleep(500);
        }
    }
}
