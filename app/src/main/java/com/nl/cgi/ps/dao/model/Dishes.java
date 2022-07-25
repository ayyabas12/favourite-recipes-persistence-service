package com.nl.cgi.ps.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tbl_dishes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Dishes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dishId;
    @NotBlank
    private String dishName;

    @NotBlank
    private Integer quantity;

    @Size(max = 5000)
    private String instructions;

    @Enumerated(EnumType.STRING)
    private CuisineCategory category;

}
