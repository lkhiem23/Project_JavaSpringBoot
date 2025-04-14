package com.lehoaikhiem.dto.Customer;

import com.lehoaikhiem.dto.NewsCategory.NewsCategoryDTO;
import com.lehoaikhiem.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
    List<NewsCategoryDTO> toDtoList(List<Customer> entities);
}
