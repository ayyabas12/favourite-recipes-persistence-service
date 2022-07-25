package com.nl.cgi.ps.model.response;

import com.nl.cgi.ps.dao.model.Dishes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishesResponse {
    List<Dishes> dishesNames;
}
