package com.nl.cgi.ps.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeIngredientRequest {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "dishId")
    private long dishId;

    @JsonProperty(value = "ingredientId")
    private long ingredientId;


}
