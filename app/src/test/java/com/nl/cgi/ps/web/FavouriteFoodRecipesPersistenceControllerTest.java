package com.nl.cgi.ps.web;

import com.nl.cgi.ps.mockdata.MockDataProvider;
import com.nl.cgi.ps.service.FavouriteFoodRecipesService;
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
public class FavouriteFoodRecipesPersistenceControllerTest {

    private static final String URI = "/food-persistence-service/food-recipes-details";
   /* private static final String SEARCH_URI = "/food-persistence-service/food-recipes-details?dishId=1";
*/
    @Mock
    FavouriteFoodRecipesService favouriteFoodRecipesService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        FavouriteFoodRecipesController favouriteFoodRecipesController = new FavouriteFoodRecipesController(favouriteFoodRecipesService);
        mockMvc = MockMvcBuilders.standaloneSetup(favouriteFoodRecipesController)
                .build();
    }

    @Nested
    @DisplayName("Get get and save call all scenario")
    class TestGetSaveDishesDetails {
        @Test
        void testGetDishesDetails() throws Exception {
            when(favouriteFoodRecipesService.getFavouriteFoodRecipes()).thenReturn(MockDataProvider.getSearchFoodRecipesDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(favouriteFoodRecipesService, times(1)).getFavouriteFoodRecipes();
        }

        //TODO
      /*  @Test
        void testSaveDishesDetails() throws Exception {
            mockMvc
                    .perform(MockMvcRequestBuilders.put(URI)
                            .content(MockDataProvider.getInvalidFavouriteFoodRecipesServiceRequest().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(favouriteFoodRecipesService, times(1)).saveFavouriteFoodDetails(any());
        }*/

        @Test
        void testDishesDetailsWhenServiceReturnEmptyList() throws Exception {
            when(favouriteFoodRecipesService.getFavouriteFoodRecipes()).thenReturn(null);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(favouriteFoodRecipesService, times(1)).getFavouriteFoodRecipes();
        }

      /*  @Test
        void testSearchDishesDetails() throws Exception {
         //   when(favouriteFoodRecipesService.getFavouriteFoodRecipesByFilterValue(MockDataProvider.getDishesServiceRequest())).thenReturn(MockDataProvider.getSearchFoodRecipesDetails());
            mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            verify(favouriteFoodRecipesService, times(1)).getFavouriteFoodRecipesByFilterValue(any());
        }*/


    }
}
