package com.nl.cgi.ps.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "tbl_ingredients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Ingredients implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientId;
    @NotBlank
    private String ingredientName;
    @NotBlank
    private String imageurl;
}
