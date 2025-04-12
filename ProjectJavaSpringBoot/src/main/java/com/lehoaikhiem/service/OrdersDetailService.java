package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.OrdersDetail.OrdersDetailDTO;
import com.lehoaikhiem.dto.OrdersDetail.OrdersDetailMapper;
import com.lehoaikhiem.entity.Orders;
import com.lehoaikhiem.entity.OrdersDetail;
import com.lehoaikhiem.repository.OrdersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersDetailService {
    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    public List<OrdersDetailDTO> findAll() {
        return ordersDetailMapper.toDtoList(ordersDetailRepository.findAll());
    }

    public OrdersDetailDTO findById(Long id) {
        Optional<OrdersDetail> ordersDetail = ordersDetailRepository.findById(id);
        return ordersDetail.map(ordersDetailMapper::toDto).orElse(null);
    }

    public OrdersDetailDTO save(OrdersDetailDTO ordersDetailDTO) {
        OrdersDetail orders = ordersDetailMapper.toEntity(ordersDetailDTO);
        OrdersDetail savedOrders = ordersDetailRepository.save(orders);
        return ordersDetailMapper.toDto(savedOrders);
    }

    public void delete(Long id) {
        ordersDetailRepository.deleteById(id);
    }
}
