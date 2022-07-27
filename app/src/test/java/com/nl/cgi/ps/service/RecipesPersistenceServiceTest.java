package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.model.Ingredients;
import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipeIngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipesRepository;
import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.model.response.RecipesResponse;
import com.nl.cgi.ps.model.response.SearchRecipesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
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
        void testGetRecipesDetails() {
            when(recipesRepository.findById(anyLong())).thenReturn(MockDataProvider.getRecipeDetailsPS());
            RecipesResponse response = recipesPersistenceService.getRecipeDetails(1L);
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getRecipes().getRecipeName()),
                    () -> assertEquals(1, response.getRecipes().getRecipeId())
            );
        }

        @Test
        void testGetEmptyRecipesDetails() {
            when(recipesRepository.findAll()).thenReturn(null);
            RecipesResponse response = recipesPersistenceService.getRecipeDetails(2);
            assertNotNull(response);
            assertNull(response.getRecipes());

        }

        @Test
        void testSaveRecipesDetailsWithInvalidIngredientsId() {
            when(ingredientsRepository.findAllById(anyList())).thenReturn(Arrays.asList(Ingredients.builder().ingredientsId(1).ingredientName("chicken").build()));
            when(recipesRepository.save(any())).thenReturn(MockDataProvider.getRecipe());
            RecipesResponse response = recipesPersistenceService.saveRecipeDetails(MockDataProvider.getRecipesServiceRequest());
            assertNull(response.getRecipes());
        }

        @Test
        void testSaveRecipesDetails() {
            when(ingredientsRepository.findAllById(anyList())).thenReturn(MockDataProvider.getIngredientsList());
            when(recipesRepository.save(any())).thenReturn(MockDataProvider.getRecipe());
            RecipesResponse response = recipesPersistenceService.saveRecipeDetails(MockDataProvider.getRecipesServiceRequest());
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getRecipes().getRecipeName()),
                    () -> assertEquals(1, response.getRecipes().getRecipeId())
            );
        }

        @Test
        void testUpdateRecipesDetailsWithEmptyResponse() {
            when(recipesRepository.save(any())).thenReturn(MockDataProvider.getRecipe());
            RecipesResponse response = recipesPersistenceService.updateRecipeDetails(anyLong(), MockDataProvider.getRecipesServiceRequest());
            assertNull(response.getRecipes());
        }

        @Test
        void testUpdateRecipesDetails() {
            when(ingredientsRepository.findAllById(anyList())).thenReturn(MockDataProvider.getIngredientsList());
            when(recipesRepository.findById(anyLong())).thenReturn(MockDataProvider.getRecipeDetailsPS());
            when(recipesRepository.save(any())).thenReturn(MockDataProvider.getRecipe());
            RecipesResponse response = recipesPersistenceService.updateRecipeDetails(1, MockDataProvider.getRecipesServiceRequest());
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getRecipes().getRecipeName()),
                    () -> assertEquals(1, response.getRecipes().getRecipeId())
            );
        }

        @Test
        void testSearchRecipesDetails() {
            when(recipesRepository.filterFavouriteFoodRecipesByFilterValue(any(), anyLong(), any())).thenReturn(MockDataProvider.getRecipeDetailsList());
            SearchRecipesResponse response = recipesPersistenceService.getFavouriteFoodRecipesByFilterValue("VEGETARIAN", 2, "example");
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getRecipes().get(0).getRecipeName()),
                    () -> assertEquals(1, response.getRecipes().get(0).getRecipeId())
            );
        }

        @Test
        void testSearchRecipesDetailsWithNullResponse() {
            when(recipesRepository.filterFavouriteFoodRecipesByFilterValue(any(), anyInt(), any())).thenReturn(null);
            SearchRecipesResponse response = recipesPersistenceService.getFavouriteFoodRecipesByFilterValue("VEGETARIAN", 2, "example");
            assertTrue(response.getRecipes().isEmpty());
        }


    }
}


