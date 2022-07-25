package com.nl.cgi.ps.mockdata;

import com.google.gson.JsonObject;
import com.nl.cgi.ps.dao.model.*;
import com.nl.cgi.ps.model.request.RecipesRequest;
import com.nl.cgi.ps.model.request.IngredientsRequest;
import com.nl.cgi.ps.model.request.RecipeIngredientRequest;
import com.nl.cgi.ps.model.response.RecipesResponse;
import com.nl.cgi.ps.model.response.IngredientsResponse;

import java.util.*;

public class MockDataProvider {


    public static RecipesResponse getDishesDetails() {
        var dishes1 = Recipes.builder().recipeId(1).recipeName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        return RecipesResponse.builder().recipes(dishes1).build();
    }
    public static Optional<Recipes> getDishesDetailsPS() {
        var dishes1 = Recipes.builder().recipeId(1).recipeName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        return Optional.of(dishes1);
    }

    public static List<Recipes> getDishesDetailsList() {
        var dishes1 = Recipes.builder().recipeId(1).recipeName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        var dishes2 = Recipes.builder().recipeId(2).recipeName("tomato soup recipe").category(CuisineCategory.VEGETARIAN).quantity(3).instructions("tomato recipe").build();
        return Arrays.asList(dishes1, dishes2);
    }


    public static JsonObject getDishesSaveRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("recipeName", "chicken breast")
                .withProperty("quantity", "2")
                .withProperty("instructions", "chicken breast make ")
                .withProperty("category", CuisineCategory.NON_VEGETARIAN.name())
                .build();
    }

    public static JsonObject getDishesUpdateRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("dishId", "1")
                .withProperty("dishName", "chicken breast")
                .withProperty("quantity", "2")
                .withProperty("instructions", "chicken breast make ")
                .withProperty("category", CuisineCategory.NON_VEGETARIAN.name())
                .build();
    }

    public static JsonObject getInvalidServiceRequest() {
        return MockJsonBuilder.aRequest()
                .build();
    }


    public static RecipesRequest getDishesServiceRequest() {
        return RecipesRequest.builder().recipeId(1).build();
    }


    public static IngredientsResponse getIngredientsDetails() {
        var ingredient = Ingredients.builder().ingredientsId(1).ingredientName("chicken").build();
        return IngredientsResponse.builder().ingredients(ingredient).build();
    }

    public static Optional<Ingredients> getIngredientsDetailsList() {
        return Optional.of(Ingredients.builder().ingredientsId(1).ingredientName("chicken").build());
    }



    public static JsonObject getIngredientsSaveRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("ingredientName", "chicken breast")
                .withProperty("imageURL", "chicken.png")
                .build();
    }

    public static JsonObject getIngredientsUpdateRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("ingredientId", "1")
                .withProperty("ingredientName", "chicken")
                .withProperty("imageURL", "chicken.png")
                .build();
    }


    public static JsonObject getFoodRecipesSaveRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("dishId", "1")
                .withProperty("ingredientId", "1")
                .build();
    }

    public static JsonObject getFoodRecipesUpdateRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("ingredientId", "1")
                .withProperty("dishId", "1")
                .withProperty("id", "1")
                .build();
    }

    public static JsonObject getFoodRecipesSearchRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("quantity", "1")
                .withProperty("instructions", "chicken with food")
                .withProperty("category", CuisineCategory.VEGETARIAN.name())
                .build();
    }

    public static RecipesRequest getDishServiceRequest() {
        return RecipesRequest.builder()
                .recipeName("Chicken")
                .category(CuisineCategory.NON_VEGETARIAN)
                .quantity(2)
                .instructions("instructions")
                .build();
    }

    public static RecipesRequest getInvalidDishesServiceRequest() {
        return RecipesRequest.builder().build();
    }


    public static IngredientsRequest getIngredientsServiceRequest() {
        return IngredientsRequest.builder()
                .ingredientName("Chicken")
                .imageURL("imageURL")
                .build();
    }

    public static IngredientsRequest getInvalidIngredientsServiceRequest() {
        return IngredientsRequest.builder()
                .imageURL("imageURL")
                .build();
    }

    public static RecipeIngredientRequest getFavouriteFoodRecipesServiceRequest() {
        return RecipeIngredientRequest.builder()
                .dishId(1)
                .ingredientId(1)
                .build();
    }

    public static RecipeIngredientRequest getInvalidFavouriteFoodRecipesServiceRequest() {
        return RecipeIngredientRequest.builder()
                .build();
    }
}
