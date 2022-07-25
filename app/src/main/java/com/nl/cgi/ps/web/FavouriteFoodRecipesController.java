package com.nl.cgi.ps.web;

import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.request.RecipeIngredientRequest;
import com.nl.cgi.ps.model.response.RecipeIngredientsResponse;
import com.nl.cgi.ps.service.FavouriteFoodRecipesService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/food-persistence-service/food-recipes-details")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class FavouriteFoodRecipesController {

    private final FavouriteFoodRecipesService favouriteFoodRecipesService;

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<RecipeIngredientsResponse> getIngredientsDetails() {
        log.info("Inside request food recipes method call");
        RecipeIngredientsResponse response = favouriteFoodRecipesService.getFavouriteFoodRecipes();
        return ResponseEntity.ok(response);
    }

    /**
     * @param recipesRequest
     * @return
     */
    @PutMapping
    public ResponseEntity<Boolean> saveOrUpdateFavouriteIngredients(@RequestBody RecipeIngredientRequest recipesRequest) {
        log.info("Inside save or update food recipes method call");
        favouriteFoodRecipesService.saveFavouriteFoodDetails(recipesRequest);
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<RecipeIngredientsResponse> getFavouriteFoodRecipesByFilterValue(@RequestBody DishesRequest request) {
        log.info("Inside filter food recipes method call");
        RecipeIngredientsResponse response = favouriteFoodRecipesService.getFavouriteFoodRecipesByFilterValue(request);
        return ResponseEntity.ok(response);
    }

    /**
     *
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<RecipeIngredientsResponse> getRecipesDetailsBySearchInstruction(@RequestParam(name = "searchString") String searchString) {
        log.info("Inside search request food recipes string");
        RecipeIngredientsResponse response= favouriteFoodRecipesService.getRecipesNamesByInstructions(searchString);
        return ResponseEntity.ok(response);
    }
}
