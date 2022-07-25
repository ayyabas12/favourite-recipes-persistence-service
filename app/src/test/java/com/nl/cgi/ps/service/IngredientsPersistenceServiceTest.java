package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.request.IngredientsRequest;
import com.nl.cgi.ps.model.response.DishesResponse;
import com.nl.cgi.ps.model.response.IngredientsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IngredientsPersistenceServiceTest {
    IngredientsPersistenceService ingredientsPersistenceService;
    @Mock
    IngredientsRepository ingredientsRepository;

    @BeforeEach
    public void setup() {
        ingredientsPersistenceService = new IngredientsPersistenceService(ingredientsRepository);
    }

    @Nested
    @DisplayName("Get and Save the dishes Details")
    class RequestGetSaveDishesDetails {
        @Test
        void testGetDishesDetails() {
            when(ingredientsRepository.findAll()).thenReturn(MockDataProvider.getIngredientsDetailsList());
            IngredientsResponse response = ingredientsPersistenceService.getIngredientsDetails();
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken", response.getIngredients().get(0).getIngredientName()),
                    () -> assertEquals(1, response.getIngredients().get(0).getIngredientId())
            );
        }

        @Test
        void testGetEmptyDishesDetails() {
            when(ingredientsRepository.findAll()).thenReturn(null);
            IngredientsResponse response = ingredientsPersistenceService.getIngredientsDetails();
            assertNotNull(response);
            assertNull(response.getIngredients());

        }

    }
}


