package com.nl.cgi.ps.web;

import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.service.RecipesPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc()
public class RecipesPersistenceControllerTest {

    private static final String URI = "/dishes-persistence-service/recipes";
    private static final String DELETE_URI = "/dishes-persistence-service/recipes?id=1";
    private static final String UPDATE_URI = "/dishes-persistence-service/recipes/1";
    private static final String SEARCH_URI = "/dishes-persistence-service/recipes/search?category=\"VEGETARIAN\"&quantity=3&instructions=\"vegetarian\"";

    @Mock
    RecipesPersistenceService recipesPersistenceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        RecipesPersistenceController recipesPersistenceController = new RecipesPersistenceController(recipesPersistenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipesPersistenceController)
                .build();
    }

    @Nested
    @DisplayName("Get get and save call all scenario")
    class TestGetSaveRecipesDetails {
        @Test
        void testGetRecipesDetails() throws Exception {
            when(recipesPersistenceService.getRecipeDetails(anyLong())).thenReturn(MockDataProvider.getRecipeDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI + "?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).getRecipeDetails(anyLong());
        }

        @Test
        void testSaveRecipesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .content(MockDataProvider.getRecipeSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).saveRecipeDetails(any());
        }

        @Test
        void testUpdateRecipesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.put(UPDATE_URI)
                            .content(MockDataProvider.getDishesUpdateRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).updateRecipeDetails(anyLong(), any());
        }

        @Test
        void testSearchRecipesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(SEARCH_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).getFavouriteFoodRecipesByFilterValue(any(), anyLong(), any());
        }

        @Test
        void testRecipesDetailsWhenServiceReturnEmptyList() throws Exception {
            when(recipesPersistenceService.getRecipeDetails(anyLong())).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI + "?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).getRecipeDetails(anyLong());
        }

        @Test
        void testDeleteRecipesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete(DELETE_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).deleteRecipes(any());
        }


    }
}
