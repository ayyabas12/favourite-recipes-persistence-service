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
        void testGetDishesDetails() throws Exception {
            when(recipesPersistenceService.getDishesDetails(anyLong())).thenReturn(MockDataProvider.getDishesDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI + "?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).getDishesDetails(anyLong());
        }

        @Test
        void testSaveDishesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .content(MockDataProvider.getDishesSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).saveDishDetails(any());
        }

        @Test
        void testDishesDetailsWhenServiceReturnEmptyList() throws Exception {
            when(recipesPersistenceService.getDishesDetails(anyLong())).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI + "?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).getDishesDetails(anyLong());
        }

        @Test
        void testDeleteDishesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete(DELETE_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(recipesPersistenceService, times(1)).deleteRecipes(any());
        }


    }
}
