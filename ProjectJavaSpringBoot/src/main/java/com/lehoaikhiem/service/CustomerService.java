package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Customer.CustomerDTO;
import com.lehoaikhiem.dto.Customer.CustomerMapper;
import com.lehoaikhiem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> findAll{
        return customerMapper.toDtoList(customerRepository.findAll());
    }
}
