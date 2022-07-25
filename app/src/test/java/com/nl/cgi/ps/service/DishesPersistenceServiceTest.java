package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.repository.DishesRepository;
import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.response.DishesResponse;
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
class DishesPersistenceServiceTest {
    DishesPersistenceService dishesPersistenceService;
    @Mock
    DishesRepository dishesRepository;

    @BeforeEach
    public void setup() {
        dishesPersistenceService = new DishesPersistenceService(dishesRepository);
    }

    @Nested
    @DisplayName("Get and Save the dishes Details")
    class RequestGetSaveDishesDetails {
        @Test
        void testGetDishesDetails() {
            when(dishesRepository.findAll()).thenReturn(MockDataProvider.getDishesDetailsList());
            DishesResponse response = dishesPersistenceService.getDishesDetails();
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("chicken breast curry", response.getDishesNames().get(0).getDishName()),
                    () -> assertEquals(1, response.getDishesNames().get(0).getDishId())
            );
        }

        @Test
        void testGetEmptyDishesDetails() {
            when(dishesRepository.findAll()).thenReturn(null);
            DishesResponse response = dishesPersistenceService.getDishesDetails();
            assertNotNull(response);
            assertNull(response.getDishesNames());

        }



    }
}


