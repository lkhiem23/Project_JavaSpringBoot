package com.lehoaikhiem.controller;

import com.lehoaikhiem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "SignUp"; // trả về trang signup.html
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(username, password, email);
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/SignUp";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "redirect:/SignUp";
        }
    }
    //Login
    @GetMapping("/login")
    public String showLoginPage() {
        return "SignIn"; // login.html
    }

}
