package com.policeschool.pluginsystem.impl;


import com.policeschool.pluginsystem.api.MyPlugin;

public class MyPluginImpl2 implements MyPlugin {
    @Override
    public void start() {
        System.out.println("This Is My Second PluginImpl, Start...");
    }

    @Override
    public void stop() {
        System.out.println("This Is My Second PluginImpl, Stop...");
    }
}
