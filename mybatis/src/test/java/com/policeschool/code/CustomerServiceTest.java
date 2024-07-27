package com.policeschool.code;

import com.policeschool.code.domain.Customer;
import com.policeschool.code.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

public class CustomerServiceTest {
    private CustomerService customerService;

    @Before
    public void init() {
        customerService = new CustomerService();
    }

    @Test
    public void test01() {
        Long customerId = customerService.register("张三", "12345678912");
        System.out.println(customerId);
        Customer customer = customerService.find(customerId);
        System.out.println(customer);
    }
}
