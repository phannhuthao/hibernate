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
public class ProductCart {
    @Id
    private int id;

    private int customerId;
    private int productId;
    private int quantity;
}
