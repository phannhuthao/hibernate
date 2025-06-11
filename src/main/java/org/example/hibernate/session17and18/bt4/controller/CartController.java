package org.example.hibernate.session17and18.bt4.controller;

import org.example.hibernate.ra.orm.entity.Product;
import org.example.hibernate.session17and18.bt4.service.CartService;
import org.example.hibernate.session17and18.bt3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/product/detail/{id}")
    public String showProductDetail(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "productDetail";
    }

    // Thêm vào giỏ hàng
    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") int productId,
                            HttpSession session,
                            @RequestParam(defaultValue = "1") int quantity,
                            Model model) {

        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            return "login"; // chưa đăng nhập -> chuyển về login
        }

        cartService.addToCart(customerId, productId, quantity);
        model.addAttribute("successMessage", "Đã thêm vào giỏ hàng!");
        return "redirect:/product/detail/" + productId;
    }

    // Hiển thị giỏ hàng
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "login";
        }

        model.addAttribute("cartItems", cartService.getCartByCustomerId(customerId));
        return "cart";
    }
}
