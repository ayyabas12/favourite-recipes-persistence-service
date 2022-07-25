package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.model.Dishes;
import com.nl.cgi.ps.dao.model.Ingredients;
import com.nl.cgi.ps.dao.model.RecipeIngredients;
import com.nl.cgi.ps.dao.repository.DishesRepository;
import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipeIngredientsRepository;
import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.request.RecipeIngredientRequest;
import com.nl.cgi.ps.model.response.RecipeIngredientsResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class FavouriteFoodRecipesService {

    private final RecipeIngredientsRepository recipeIngredientsRepository;

    private final IngredientsRepository ingredientsRepository;

    private final DishesRepository dishesRepository;

    /**
     * @return
     */
    public RecipeIngredientsResponse getFavouriteFoodRecipes() {
        List<RecipeIngredients> foodRecipeDetails = recipeIngredientsRepository.findAll();
        return RecipeIngredientsResponse.builder().foodRecipeDetails(foodRecipeDetails).build();
    }

    /**
     * @return
     */
    public RecipeIngredientsResponse getFavouriteFoodRecipesByFilterValue(DishesRequest request) {
        List<RecipeIngredients> foodRecipeDetails = recipeIngredientsRepository.filterFavouriteFoodRecipesByFilterValue(request.getCategory().name(), request.getQuantity(), request.getIngredientId());
        return RecipeIngredientsResponse.builder().foodRecipeDetails(foodRecipeDetails).build();
    }

    /**
     * @param request
     */
    public void saveFavouriteFoodDetails(RecipeIngredientRequest request) {
        var ingredients = ingredientsRepository.findById(request.getIngredientId());
        var dishes = dishesRepository.findById(request.getDishId());
        log.debug(" Recipes details is not found and save the Recipes{}", request.getId());
        recipeIngredientsRepository.save(buildRecipesDetails(dishes.get(), ingredients.get()));
    }

    /**
     * @param recipeId
     */
    public void deleteFavouriteFood(Long recipeId) {
        recipeIngredientsRepository.deleteById(recipeId);
    }

    public RecipeIngredientsResponse getRecipesNamesByInstructions(String searchString) {
        return RecipeIngredientsResponse.builder().foodRecipeDetails(recipeIngredientsRepository.filterFavouriteFoodRecipesByFilterInstructions(searchString)).build();
    }

    /**
     * @param request
     * @return
     */
    private RecipeIngredients buildRecipesDetails(Dishes recipe, Ingredients ingredients) {
        return RecipeIngredients.builder()
                .dishes(recipe)
                .ingredients(ingredients)
                .build();
    }


}
