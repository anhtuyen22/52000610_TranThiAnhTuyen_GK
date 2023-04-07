package com.trantuyen.springecommerce.service;

import com.trantuyen.springecommerce.entity.Customer;
import com.trantuyen.springecommerce.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailService")
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepo.findByUsername(username);
        if (customer.isEmpty())
            throw new UsernameNotFoundException("Can't not find user by username");
        return customer.get();
    }
}