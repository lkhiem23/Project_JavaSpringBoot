//package com.lehoaikhiem.controller;
//
//import com.lehoaikhiem.service.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/admin") //
//public class ViewController {
//
//    private final ProductService productService;
//    private final OrderService orderService;
//    private final CustomerService customerService;
//    private final CategoryService categoryService;
//    private final NewsService newsService;
//
//    @Autowired
//    public ViewController(ProductService productService,
//                          OrderService orderService,
//                          CustomerService customerService,
//                          CategoryService categoryService,
//                          NewsService newsService) {
//        this.productService = productService;
//        this.orderService = orderService;
//        this.customerService = customerService;
//        this.categoryService = categoryService;
//        this.newsService = newsService;
//
//    }
//
//    // Endpoint cho trang dashboard: localhost:8181/admin
//    @GetMapping
//    public String adminDashboard(Model model) {
//        model.addAttribute("totalProducts", productService.countAllProducts());
//        model.addAttribute("totalOrders", orderService.countAllOrders());
//        model.addAttribute("totalCustomers", customerService.countAllCustomers());
//        model.addAttribute("totalNews", newsService.countAllNews());
//        model.addAttribute("totalCategory", categoryService.countAllCategories());
//
//        return "admin/dashboard";
//    }
//
//    // localhost:8181/admin/products
//    @GetMapping("/products")
//    public String adminProducts(Model model) {
//        model.addAttribute("products", productService.findAllWithoutPaging());
//        return "admin/products";
//    }
//
//    // localhost:8181/admin/categories
//    @GetMapping("/categories")
//    public String adminCategories() {
//        return "admin/categories";
//    }
//
//    // localhost:8181/admin/orders
//    @GetMapping("/orders")
//    public String adminOrders() {
//        return "admin/orders";
//    }
//
//    // localhost:8181/admin/users
//    @GetMapping("/users")
//    public String adminUsers() {
//        return "admin/users";
//    }
//}