package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipeIngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipesRepository;
import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.model.response.RecipesResponse;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RecipesPersistenceServiceTest {
    RecipesPersistenceService recipesPersistenceService;
    @Mock
    RecipesRepository recipesRepository;

    @Mock
    RecipeIngredientsRepository recipeIngredientsRepository;

    @Mock
    IngredientsRepository ingredientsRepository;


    @BeforeEach
    public void setup() {
        recipesPersistenceService = new RecipesPersistenceService(recipesRepository, recipeIngredientsRepository, ingredientsRepository);
    }

    @Nested
    @DisplayName("Get and Save the dishes Details")
    class RequestGetSaveRecipesDetails {
        @Test
        void testGetDishesDetails() {
            when(recipesRepository.findById(anyLong())).thenReturn(MockDataProvider.getDishesDetailsPS());
            RecipesResponse response = recipesPersistenceService.getDishesDetails(1L);
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getRecipes().getRecipeName()),
                    () -> assertEquals(1, response.getRecipes().getRecipeId())
            );
        }

        @Test
        void testGetEmptyDishesDetails() {
            when(recipesRepository.findAll()).thenReturn(null);
            RecipesResponse response = recipesPersistenceService.getDishesDetails(2);
            assertNotNull(response);
            assertNull(response.getRecipes());

        }



    }
}


