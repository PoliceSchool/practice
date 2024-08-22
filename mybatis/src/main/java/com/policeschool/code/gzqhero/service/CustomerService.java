package com.policeschool.code.gzqhero.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.policeschool.code.gzqhero.DaoUtils;
import com.policeschool.code.gzqhero.dao.CustomerMapper;
import com.policeschool.code.gzqhero.domain.Customer;

public class CustomerService {
    public Long register(String name, String phone) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "name is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(phone), "phone is empty");
        return DaoUtils.execute(sqlSession -> {
            CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            int affected = mapper.save(customer);
            if (affected < 0) {
                throw new RuntimeException("Save Customer fail...");
            }
            return customer.getId();
        });
    }

    public Customer find(long id) {
        Preconditions.checkArgument(id > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
            return mapper.find(id);
        });
    }
}
