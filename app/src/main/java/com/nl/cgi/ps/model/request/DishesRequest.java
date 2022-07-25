package com.nl.cgi.ps.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nl.cgi.ps.dao.model.CuisineCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishesRequest {

    @JsonProperty(value = "dishId")
    private long dishId;

    @JsonProperty(value = "dishName")
    private String dishName;

    @JsonProperty(value = "quantity")
    private Integer quantity;

    @JsonProperty(value = "instructions")
    private String instructions;

    @JsonProperty(value = "category")
    private CuisineCategory category;

    @JsonProperty(value = "ingredientId")
    private List<Long> ingredientId;


}
