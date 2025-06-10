package org.example.hibernate.session17.bt1andbt2.controller;

import org.example.hibernate.ra.orm.entity.Customer;
import org.example.hibernate.session17.bt1andbt2.dto.CustomerForm;
import org.example.hibernate.session17.bt1andbt2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/bt1")
    public String showRegister(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "register";
    }

    @PostMapping("/bt1")
    public String registerUser(@Valid @ModelAttribute("customerForm") CustomerForm form,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        authService.register(form);
        model.addAttribute("successMessage", "Đăng ký thành công!");
        model.addAttribute("customerForm", new CustomerForm());
        return "register";
    }

    @GetMapping("/bt2")
    public String showLogin(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "login";
    }

    @PostMapping("/bt2")
    public String loginUser(@Valid @ModelAttribute("customerForm") CustomerForm form,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        Customer customer = authService.login(form.getUsername(), form.getPassword());

        if (customer == null) {
            model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "login";
        }

        if ("ADMIN".equalsIgnoreCase(customer.getRole())) {
            return "admin";
        } else if ("USER".equalsIgnoreCase(customer.getRole())) {
            return "home";
        } else {
            model.addAttribute("errorMessage", "Không xác định quyền truy cập.");
            return "login";
        }
    }
}
