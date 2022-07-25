package com.nl.cgi.ps.model.response;

import com.nl.cgi.ps.dao.model.CuisineCategory;
import com.nl.cgi.ps.dao.model.Dishes;
import com.nl.cgi.ps.dao.model.Ingredients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FavRecipeIngredients {


    private long id;

    private Integer quantity;

    private String instructions;

    private CuisineCategory category;

    private Dishes dishes;

    private Ingredients ingredients;

    FavRecipeIngredients() {

    }
}
