package org.example.hibernate.ra.orm.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    private int id;

    private String productName;
    private String description;
    private int price;
    private int stock;
    private String image;
}
