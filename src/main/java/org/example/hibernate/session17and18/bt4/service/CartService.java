package org.example.hibernate.session17and18.bt4.service;

import org.example.hibernate.ra.orm.entity.ProductCart;
import org.example.hibernate.session17and18.bt4.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Transactional
    public void addToCart(int customerId, int productId, int quantity) {
        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        ProductCart existingCartItem = cartDao.findByCustomerIdAndProductId(customerId, productId);

        if (existingCartItem != null) {
            // Nếu có, cập nhật số lượng
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartDao.update(existingCartItem);
        } else {
            // Nếu chưa có, thêm mới
            ProductCart newCartItem = new ProductCart();
            newCartItem.setCustomerId(customerId);
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(quantity);
            cartDao.save(newCartItem);
        }
    }

    public List<ProductCart> getCartByCustomerId(int customerId) {
        return cartDao.findByCustomerId(customerId);
    }
}
