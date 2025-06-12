package org.example.hibernate.ra.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên phim không được để trống")
    private String title;
    @NotBlank(message = "Tên đạo diễn không được để trống")
    private String director;
    @Size(min = 1900, max = 2025, message = "Năm phát hành phải từ 1900 đến năm hiện tại")
    private int releaseYear;
    private String genre;

    @Min(value = 1, message = "Thời lượng phải lớn hơn 0")
    private int duration;

    @Pattern(regexp = "^(http|https)://.*$", message = "URL không hợp lệ")
    private String poster;

    private boolean status;

}
