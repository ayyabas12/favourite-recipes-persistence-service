package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.mockdata.MockDataProvider;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    @DisplayName("Get and Save the Ingredients Details")
    class RequestGetSaveIngredientsDetails {
        @Test
        void testGetIngredientsDetails() {
            when(ingredientsRepository.findById(anyLong())).thenReturn(MockDataProvider.getIngredientsDetailsList());
            IngredientsResponse response = ingredientsPersistenceService.getIngredientsDetails(1l);
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken", response.getIngredients().getIngredientName()),
                    () -> assertEquals(1, response.getIngredients().getIngredientsId())
            );
        }

        @Test
        void testSaveIngredientsDetails() {
            when(ingredientsRepository.save(any())).thenReturn(MockDataProvider.getIngredientForSave());
            IngredientsResponse response = ingredientsPersistenceService.saveIngredient(MockDataProvider.getIngredientsServiceRequest());
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken", response.getIngredients().getIngredientName()),
                    () -> assertEquals(1, response.getIngredients().getIngredientsId())
            );
        }

        @Test
        void testUpdateIngredientsDetails() {
            when(ingredientsRepository.findById(anyLong())).thenReturn(MockDataProvider.getIngredientsDetailsList());
            when(ingredientsRepository.save(any())).thenReturn(MockDataProvider.getIngredientForSave());
            IngredientsResponse response = ingredientsPersistenceService.updateIngredient(anyLong(), MockDataProvider.getIngredientsServiceRequest());
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken", response.getIngredients().getIngredientName()),
                    () -> assertEquals(1, response.getIngredients().getIngredientsId())
            );
        }

        @Test
        void testGetEmptyDishesDetails() {
            when(ingredientsRepository.findById(anyLong())).thenReturn(null);
            IngredientsResponse response = ingredientsPersistenceService.getIngredientsDetails(2);
            assertNotNull(response);
            assertNull(response.getIngredients());

        }

    }
}


