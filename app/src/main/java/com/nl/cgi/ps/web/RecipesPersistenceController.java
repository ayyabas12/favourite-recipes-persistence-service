package com.nl.cgi.ps.web;

import com.nl.cgi.ps.model.request.RecipesRequest;
import com.nl.cgi.ps.model.response.RecipesResponse;
import com.nl.cgi.ps.model.response.SearchRecipesResponse;
import com.nl.cgi.ps.service.RecipesPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dishes-persistence-service/recipes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class RecipesPersistenceController {

    private final RecipesPersistenceService recipesPersistenceService;

    /**
     * @return list of recipe details
     */
    @GetMapping
    public ResponseEntity<RecipesResponse> getRecipesDetails(final @RequestParam(name = "id") long id) {
        log.info("Inside request recipe call");
        RecipesResponse response = recipesPersistenceService.getDishesDetails(id);
        return ResponseEntity.ok(response);
    }

    /**
     * @param recipesRequest to save
     * @return saved recipe
     */
    @PostMapping
    public ResponseEntity<RecipesResponse> saveRecipesDetails(@RequestBody RecipesRequest recipesRequest) {
        log.info("Inside save Recipes method call");
        return ResponseEntity.ok(recipesPersistenceService.saveDishDetails(recipesRequest));
    }

    /**
     * @param recipesRequest to update
     * @return updated recipe
     */
    @PutMapping("{id}")
    public ResponseEntity<RecipesResponse> updateRecipesDetails(final @PathVariable(name = "id") long id, @RequestBody RecipesRequest recipesRequest) {
        log.info("Inside update Recipes method call");
        return ResponseEntity.ok(recipesPersistenceService.updateRecipeDetails(id, recipesRequest));
    }

    /**
     * @return boolean
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteRecipesDetails(@RequestParam(name = "id") long id) {
        log.info("Inside delete dishes method call");
        recipesPersistenceService.deleteRecipes(id);
        return ResponseEntity.ok(true);
    }

    /**
     *
     * @param category type
     * @param quantity number of serve
     * @param instructions to prepare
     * @return filter recipe details
     */

    @GetMapping("/search")
    public ResponseEntity<SearchRecipesResponse> getFavouriteFoodRecipesByFilterValue(@RequestParam("category") String category, @RequestParam("quantity") long quantity, @RequestParam("instructions") String instructions) {
        log.info("Inside filter food recipes method call");
        return ResponseEntity.ok(recipesPersistenceService.getFavouriteFoodRecipesByFilterValue(category, quantity, instructions));
    }


}
