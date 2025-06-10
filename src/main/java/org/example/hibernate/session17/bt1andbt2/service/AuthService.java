package org.example.hibernate.session17.bt1andbt2.service;

import org.example.hibernate.ra.orm.entity.Customer;
import org.example.hibernate.session17.bt1andbt2.dao.AuthDao;
import org.example.hibernate.session17.bt1andbt2.dto.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class AuthService {
    @Autowired
    private AuthDao authDao;

    @Transactional
    public void register(@Valid CustomerForm form) {
        Customer customer = new Customer();
        customer.setUsername(form.getUsername());
        customer.setPassword(form.getPassword());
        customer.setEmail(form.getEmail());
        customer.setPhoneNumber(form.getPhoneNumber());
        customer.setRole("USER");
        customer.setStatus(true);
        authDao.save(customer);
    }

    public Customer login(String username, String password) {
        return authDao.findByUsernameAndPassword(username, password);
    }
}
