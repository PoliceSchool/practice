package com.policeschool.code.gzqhero;

import com.policeschool.code.gzqhero.domain.Customer;
import com.policeschool.code.gzqhero.service.CustomerService;
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
