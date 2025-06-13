package org.example.hibernate.ra.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seed", uniqueConstraints = {@UniqueConstraint(columnNames = "seedName")})
public class Seed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên tối đa 100 ký tự")
    private String seedName;

    private String description;

    @Min(value = 1, message = "Giá tiền phải lớn hơn 0")
    private int price;

    @Min(value = 0, message = "Tồn kho phải >= 0")
    private int stock;

    private String image;
}