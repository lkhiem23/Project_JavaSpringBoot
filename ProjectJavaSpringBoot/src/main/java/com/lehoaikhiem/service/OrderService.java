package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Orders.OrdersDTO;
import com.lehoaikhiem.dto.Orders.OrdersMapper;
import com.lehoaikhiem.entity.Orders;
import com.lehoaikhiem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    public List<OrdersDTO> findAll(){
        return ordersMapper.toDtoList(orderRepository.findAll());
    }

    public OrdersDTO findByOrderId(Long id){
        Optional<Orders> orders = orderRepository.findById(id);
        return orders.map(ordersMapper::toDto).orElse(null);
    }

    public OrdersDTO save(OrdersDTO ordersDTO){
        Orders orders = ordersMapper.toEntity(ordersDTO);
        Orders savedOrders = orderRepository.save(orders);
        return ordersMapper.toDto(savedOrders);
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }
    public long countAllOrders() {
        return orderRepository.count();
    }
}
