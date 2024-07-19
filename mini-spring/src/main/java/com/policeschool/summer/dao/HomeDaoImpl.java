package com.policeschool.summer.dao;


import com.policeschool.summer.annotation.Repository;

/**
 * @author ljx
 */
@Repository("homeDaoImpl")
public class HomeDaoImpl implements HomeDao {
    @Override
    public String getWelcomeMsg() {
        System.out.println("execute HomeDaoImpl.getWelcomeMsg()");
        return "Welcome to Summer Framework";
    }
}
