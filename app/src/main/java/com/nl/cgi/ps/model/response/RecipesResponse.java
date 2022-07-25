package com.nl.cgi.ps.model.response;

import com.nl.cgi.ps.dao.model.Recipes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipesResponse {
    Recipes recipes;
}
