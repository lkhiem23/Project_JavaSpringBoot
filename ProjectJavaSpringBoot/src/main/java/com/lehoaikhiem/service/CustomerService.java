package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Customer.CustomerDTO;
import com.lehoaikhiem.dto.Customer.CustomerMapper;
import com.lehoaikhiem.dto.NewsCategory.NewsCategoryDTO;
import com.lehoaikhiem.entity.Customer;
import com.lehoaikhiem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> findAll(){
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    public CustomerDTO findById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toCustomerDTO).orElse(null);
    }

    public CustomerDTO save(CustomerDTO customerDTO){
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerMapper.toCustomerDTO(customerRepository.save(customer));
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }
}
