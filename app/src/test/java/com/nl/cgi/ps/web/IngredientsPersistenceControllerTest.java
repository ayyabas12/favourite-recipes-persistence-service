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

    private static final String GET_URI = "/ingredients-persistence-service/ingredients-details?id=1";
    private static final String SAVE_URI = "/ingredients-persistence-service/ingredients-details";

    private static final String UPDATE_URI = "/ingredients-persistence-service/ingredients-details/1";


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
            when(ingredientsPersistenceService.getIngredientsDetails(anyLong())).thenReturn(MockDataProvider.getIngredientsDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(GET_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).getIngredientsDetails(anyLong());
        }

        @Test
        void testSaveIngredientsDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.post(SAVE_URI)
                            .content(MockDataProvider.getIngredientsSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).saveIngredient(any());
        }

        @Test
        void testUpdateIngredientsDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.put(UPDATE_URI)
                            .content(MockDataProvider.getIngredientsSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).updateIngredient(anyLong(), any());
        }


        @Test
        void testIngredientsDetailsWhenServiceReturnEmptyList() throws Exception {
            when(ingredientsPersistenceService.getIngredientsDetails(anyLong())).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(GET_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(ingredientsPersistenceService, times(1)).getIngredientsDetails(anyLong());
        }

    }
}
