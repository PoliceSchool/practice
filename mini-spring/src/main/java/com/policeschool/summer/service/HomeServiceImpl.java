package com.policeschool.summer.service;


import com.policeschool.summer.annotation.Autowired;
import com.policeschool.summer.annotation.Service;
import com.policeschool.summer.dao.HomeDao;

/**
 * @author ljx
 */
@Service("homeServiceImpl")
public class HomeServiceImpl implements HomeService {
    @Autowired("homeDaoImpl")
    private HomeDao homeDao;


    @Override
    public void index() {
        System.out.println("execute HomeServiceImpl.index()");
        System.out.println(homeDao.getWelcomeMsg());
    }
}
