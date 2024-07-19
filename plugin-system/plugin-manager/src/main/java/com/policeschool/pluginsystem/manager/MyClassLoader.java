package com.policeschool.pluginsystem.manager;

import com.policeschool.pluginsystem.api.MyPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyClassLoader extends URLClassLoader {
    private final Set<String> urlPaths = new HashSet<>(2);
    private static final Map<String, MyPlugin> PLUGINS = new HashMap<>();


    public MyClassLoader(String jarPath) throws MalformedURLException {
        super(new URL[]{new URL(buildJarPath(jarPath))});
        urlPaths.add(buildJarPath(jarPath));
    }

    private static String buildJarPath(String jarPath) {
        return "file:" + jarPath;
    }

    public MyPlugin loadPlugin(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (!PLUGINS.containsKey(className)) {
            synchronized (PLUGINS) {
                if (!PLUGINS.containsKey(className)) {
                    PLUGINS.put(className, (MyPlugin) loadClass(className).newInstance());
                }
            }
        }
        return PLUGINS.get(className);
    }

    public synchronized void addJarPath(String jarPath) throws MalformedURLException {
        if (!urlPaths.contains(jarPath)) {
            addURL(new URL(buildJarPath(jarPath)));
            urlPaths.add(buildJarPath(jarPath));
        }
    }

    public boolean containJarPath(String jarPath) {
        return urlPaths.contains(buildJarPath(jarPath));
    }
}
