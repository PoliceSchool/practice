package com.policeschool.pluginsystem.impl;


import com.policeschool.pluginsystem.api.MyPlugin;

public class MyPluginImpl implements MyPlugin {
    @Override
    public void start() {
        System.out.println("This Is My First PluginImpl, Start...");
    }

    @Override
    public void stop() {
        System.out.println("This Is My First PluginImpl, Stop...");
    }
}
