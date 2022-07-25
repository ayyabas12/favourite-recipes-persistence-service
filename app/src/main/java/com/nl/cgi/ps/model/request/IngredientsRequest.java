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
public class IngredientsRequest {

    @JsonProperty(value = "ingredientName")
    private String ingredientName;
    @JsonProperty(value = "imageURL")
    private String imageURL;
}
