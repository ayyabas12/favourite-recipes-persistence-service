package com.nl.cgi.ps.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Recipes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;
    @NotBlank
    private String recipeName;

    @NotBlank
    private Integer quantity;

    @Size(max = 5000)
    private String instructions;

    @Enumerated(EnumType.STRING)
    private CuisineCategory category;

    @Transient
    private List<Long> ingredientsList;

}
