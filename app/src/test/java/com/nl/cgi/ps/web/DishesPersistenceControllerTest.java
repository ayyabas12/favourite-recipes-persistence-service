package com.nl.cgi.ps.web;

import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.service.DishesPersistenceService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc()
public class DishesPersistenceControllerTest {

    private static final String URI = "/dishes-persistence-service/recipes-details";
    private static final String DELETE_URI = "/dishes-persistence-service/recipes-details?dishId=1";

    @Mock
    DishesPersistenceService dishesPersistenceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        DishesPersistenceController dishesPersistenceController = new DishesPersistenceController(dishesPersistenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(dishesPersistenceController)
                .build();
    }

    @Nested
    @DisplayName("Get get and save call all scenario")
    class TestGetSaveDishesDetails {
        @Test
        void testGetDishesDetails() throws Exception {
            when(dishesPersistenceService.getDishesDetails()).thenReturn(MockDataProvider.getDishesDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(dishesPersistenceService, times(1)).getDishesDetails();
        }

        @Test
        void testSaveDishesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.put(URI)
                            .content(MockDataProvider.getDishesSaveRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(dishesPersistenceService, times(1)).saveDishDetails(any());
        }

        @Test
        void testDishesDetailsWhenServiceReturnEmptyList() throws Exception {
            when(dishesPersistenceService.getDishesDetails()).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(dishesPersistenceService, times(1)).getDishesDetails();
        }

        @Test
        void testDeleteDishesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete(DELETE_URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(dishesPersistenceService, times(1)).deleteRecipes(any());
        }


    }
}
