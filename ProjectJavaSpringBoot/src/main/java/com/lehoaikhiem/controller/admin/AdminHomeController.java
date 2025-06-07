package com.lehoaikhiem.controller.admin;

import com.lehoaikhiem.service.CustomerService;
import com.lehoaikhiem.service.NewsService;
import com.lehoaikhiem.service.OrderService;
import com.lehoaikhiem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminHomeController {
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final NewsService newsService;

    public AdminHomeController(OrderService orderService,
                               ProductService productService,
                               CustomerService customerService,
                               NewsService newsService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
        this.newsService = newsService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> map = new HashMap<>();
        map.put("totalOrders", orderService.countAllOrders());
        map.put("totalProducts", productService.countAllProducts());
        map.put("totalCustomers", customerService.countAllCustomers());
        map.put("totalNews", newsService.countAllNews());
        return ResponseEntity.ok(map);
    }
}
