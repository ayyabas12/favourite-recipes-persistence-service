package com.nl.cgi.ps.web;

import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.service.IngredientsPersistenceService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc()
public class IngredientsPersistenceControllerTest {

    private static final String URI = "/ingredients-persistence-service/ingredients-details";

    @Mock
    IngredientsPersistenceService ingredientsPersistenceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        IngredientsPersistenceController ingredientsPersistenceController = new IngredientsPersistenceController(ingredientsPersistenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientsPersistenceController)
                .build();
    }

    @Nested
    @DisplayName("Get get and save call all scenario")
    class TestGetSaveIngredientsDetails {
        @Test
        void testGetIngredientsDetails() throws Exception {
            when(ingredientsPersistenceService.getIngredientsDetails()).thenReturn(MockDataProvider.getIngredientsDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).getIngredientsDetails();
        }

        @Test
        void testSaveIngredientsDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .content(MockDataProvider.getIngredientsSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).saveIngredientsDetails(any());
        }

        @Test
        void testIngredientsDetailsWhenServiceReturnEmptyList() throws Exception {
            when(ingredientsPersistenceService.getIngredientsDetails()).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).getIngredientsDetails();
        }

    }
}
