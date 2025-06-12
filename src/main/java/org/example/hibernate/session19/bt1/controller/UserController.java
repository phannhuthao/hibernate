package org.example.hibernate.session19.bt1.controller;

import org.example.hibernate.ra.orm.entity.User;
import org.example.hibernate.session19.bt1.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {
    private final UserDao userDao = new UserDao();
    private static final int PAGE_SIZE = 5;

    @GetMapping("/user")
    public String showUser(@RequestParam(defaultValue = "1") int page, Model model) {
        List<User> users = userDao.findUsersWithPagination(page, PAGE_SIZE);
        long totalUsers = userDao.countUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / PAGE_SIZE);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "user";
    }

    @PostMapping("/user/{id}/toggle")
    public String toggleUser(@PathVariable Long id,
                             @RequestParam boolean activate,
                             @RequestParam int page) {
        userDao.updateUserStatus(id, activate);
        return "redirect:/user?page=" + page;
    }
}

