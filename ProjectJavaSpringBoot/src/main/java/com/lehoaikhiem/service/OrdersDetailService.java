package com.lehoaikhiem.service;

import com.lehoaikhiem.repository.OrdersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersDetailService {
    @Autowired
    private OrdersDetailRepository ordersDetailRepository;


}
