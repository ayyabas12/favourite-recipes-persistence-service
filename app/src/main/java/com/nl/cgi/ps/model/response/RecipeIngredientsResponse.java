package com.nl.cgi.ps.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nl.cgi.ps.dao.model.RecipeIngredients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeIngredientsResponse {
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
    List<RecipeIngredients> foodRecipeDetails;
}
