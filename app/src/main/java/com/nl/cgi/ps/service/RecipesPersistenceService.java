package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.model.Ingredients;
import com.nl.cgi.ps.dao.model.RecipeIngredients;
import com.nl.cgi.ps.dao.model.Recipes;
import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipeIngredientsRepository;
import com.nl.cgi.ps.dao.repository.RecipesRepository;
import com.nl.cgi.ps.model.request.RecipesRequest;
import com.nl.cgi.ps.model.response.RecipesResponse;
import com.nl.cgi.ps.model.response.SearchRecipesResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class RecipesPersistenceService {

    private final RecipesRepository recipesRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    private final IngredientsRepository ingredientsRepository;

    /**
     * @return recipes details
     */
    public RecipesResponse getRecipeDetails(final long id) {
        var dishes = recipesRepository.findById(id);
        var ingredients = recipeIngredientsRepository.getAllIngredientsByRecipeID(id);
        if (dishes.isPresent()) {
            dishes.get().setIngredientsList(ingredients);
            log.debug(" get recipes details successfully {}", dishes.get().getRecipeId());
            return buildRecipesResponse(dishes.get());
        }
        log.debug(" No recipes details exits");
        return emptyDataRecipesResponse();
    }


    /**
     * @param request recipes request
     */
    public RecipesResponse saveRecipeDetails(RecipesRequest request) {

        if (!isValidIngredientsId(request)) {
            return emptyDataRecipesResponse();
        }
        Recipes recipes = recipesRepository.save(buildRecipesRequest(request));
        if (recipes != null) {
            saveRecipes(request, recipes);
            log.debug("Recipes details saved successfully {}", request.getRecipeId());
            return buildRecipesResponse(recipes);
        } else {
            log.debug("Recipes detail is not saved successfully {}", request.getRecipeId());
            return emptyDataRecipesResponse();
        }
    }

    private boolean isValidIngredientsId(RecipesRequest request) {
        var ingredient = ingredientsRepository.findAllById(request.getIngredientId());
        if (ingredient != null && ingredient.size() != request.getIngredientId().size()) {
            log.error("invalid ingredientsIds {}", request.getIngredientId());
            return false;
        }
        return true;
    }

    /**
     * @param recipeId to delete the data
     * @param request  update
     * @return update recipes details
     */
    public RecipesResponse updateRecipeDetails(final long recipeId, final RecipesRequest request) {
        Optional<Recipes> recipes = recipesRepository.findById(recipeId);
        if (recipes.isPresent() && isValidIngredientsId(request)) {
            recipeIngredientsRepository.deleteByRecipeId(recipeId);
            Recipes updateRecipe = recipes.get();
            updateRecipe.setRecipeName(request.getRecipeName());
            updateRecipe.setInstructions(request.getInstructions());
            updateRecipe.setCategory(request.getCategory());
            Recipes savedRecipes = recipesRepository.save(updateRecipe);
            saveRecipes(request, savedRecipes);
            log.debug("Recipes details update successfully {}", request.getRecipeId());
            return buildRecipesResponse(savedRecipes);
        }
        log.debug("Recipes detail is not update successfully {}", request.getRecipeId());
        return emptyDataRecipesResponse();
    }

    private void saveRecipes(RecipesRequest request, Recipes recipes) {
        List<RecipeIngredients> recipeIngredients = new ArrayList<>();
        for (long id : request.getIngredientId()) {
            RecipeIngredients re = new RecipeIngredients();
            re.setIngredients(Ingredients.builder().ingredientsId(id).build());
            re.setRecipes(recipes);
            recipeIngredients.add(re);
        }
        recipeIngredientsRepository.saveAll(recipeIngredients);
    }

    /**
     * @param recipeId unique id to delete recipe
     */
    public void deleteRecipes(Long recipeId) {
        recipesRepository.deleteById(recipeId);
    }


    /**
     * @return filter the recipe details
     */
    public SearchRecipesResponse getFavouriteFoodRecipesByFilterValue(String category, long quantity, String instructions) {
        List<Recipes> foodRecipeDetails = recipesRepository.filterFavouriteFoodRecipesByFilterValue(category, quantity, instructions);
        return SearchRecipesResponse.builder().recipes(foodRecipeDetails).build();
    }


    /**
     * @param request build
     * @return recipe request
     */
    private Recipes buildRecipesRequest(RecipesRequest request) {
        return Recipes.builder().
                recipeName(request.getRecipeName())
                .category(request.getCategory())
                .instructions(StringUtils.isNotEmpty(request.getInstructions()) ? request.getInstructions().strip() : "")
                .quantity(request.getQuantity())
                .build();
    }

    /**
     * @param recipes from db
     * @return build the recipe response
     */
    private RecipesResponse buildRecipesResponse(Recipes recipes) {
        return RecipesResponse.builder().recipes(recipes).build();
    }

    /**
     * @return empty recipe details
     */
    private RecipesResponse emptyDataRecipesResponse() {
        return RecipesResponse.builder().build();
    }


}
