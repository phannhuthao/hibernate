package org.example.hibernate.session17.bt3.controller;

import org.example.hibernate.ra.orm.entity.Product;
import org.example.hibernate.session17.bt3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/bt3")
    public String showProductPage(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {
        List<Product> products = productService.getProducts(page, size);
        long totalProducts = productService.countProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "product";
    }

    @GetMapping("/bt3/detail/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            model.addAttribute("errorMessage", "Không tìm thấy sản phẩm");
            return "error";
        }
        model.addAttribute("product", product);
        return "productDetail";
    }
}
