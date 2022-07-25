package com.nl.cgi.ps.mockdata;

import com.google.gson.JsonObject;
import com.nl.cgi.ps.dao.model.*;
import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.request.IngredientsRequest;
import com.nl.cgi.ps.model.request.RecipeIngredientRequest;
import com.nl.cgi.ps.model.response.DishesResponse;
import com.nl.cgi.ps.model.response.IngredientsResponse;
import com.nl.cgi.ps.model.response.RecipeIngredientsResponse;


import java.util.*;

public class MockDataProvider {



    public static DishesResponse getDishesDetails(){
        var dishes1 = Dishes.builder().dishId(1).dishName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        var dishes2 = Dishes.builder().dishId(2).dishName("tomato soup recipe").category(CuisineCategory.VEGETARIAN).quantity(3).instructions("tomato recipe").build();
        return DishesResponse.builder().dishesNames(Arrays.asList(dishes1,dishes2)).build();
    }

    public static List<Dishes> getDishesDetailsList(){
        var dishes1 = Dishes.builder().dishId(1).dishName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        var dishes2 = Dishes.builder().dishId(2).dishName("tomato soup recipe").category(CuisineCategory.VEGETARIAN).quantity(3).instructions("tomato recipe").build();
        return Arrays.asList(dishes1,dishes2);
    }


    public static JsonObject getDishesSaveRequest() {
        return MockJsonBuilder.aRequest()
                .withProperty("dishName", "chicken breast")
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


    public static DishesRequest getDishesServiceRequest() {
        return DishesRequest.builder().dishId(1).build();
    }


    public static IngredientsResponse getIngredientsDetails() {
        var ingredient1 = Ingredients.builder().ingredientId(1).ingredientName("chicken").build();
        var ingredient2 = Ingredients.builder().ingredientId(2).ingredientName("tomoto").build();
        return IngredientsResponse.builder().ingredients(Arrays.asList(ingredient1, ingredient2)).build();
    }

    public static List<Ingredients> getIngredientsDetailsList() {
        var ingredient1 = Ingredients.builder().ingredientId(1).ingredientName("chicken").build();
        var ingredient2 = Ingredients.builder().ingredientId(2).ingredientName("tomoto").build();
        return Arrays.asList(ingredient1, ingredient2);
    }

  /*  public static RecipeIngredientsResponse getFoodRecipesDetails() {
        var dishes1 = Dishes.builder().dishId(1).dishName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        Map<Dishes, List<Ingredients>> foodRecipeDetails= new HashMap<>();
        foodRecipeDetails.put(dishes1, getIngredientsDetails().getIngredients());
        return RecipeIngredientsResponse.builder().foodRecipeDetails(foodRecipeDetails).build();
    }*/
   public static RecipeIngredientsResponse getSearchFoodRecipesDetails(){
        var dishes = Dishes.builder().dishId(1).dishName("chicken breast curry").category(CuisineCategory.NON_VEGETARIAN).quantity(2).instructions("chicken recipe").build();
        var ingredient = Ingredients.builder().ingredientId(2).ingredientName("tomoto").build();
        List<RecipeIngredients> foodRecipeDetails =new ArrayList<>();
        foodRecipeDetails.add(RecipeIngredients.builder().id(1).dishes(dishes).ingredients(ingredient).build());
        return RecipeIngredientsResponse.builder().foodRecipeDetails(foodRecipeDetails).build();

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

    public static DishesRequest getDishServiceRequest() {
        return DishesRequest.builder()
                .dishName("Chicken")
                .category(CuisineCategory.NON_VEGETARIAN)
                .quantity(2)
                .instructions("instructions")
                .build();
    }

    public static DishesRequest getInvalidDishesServiceRequest() {
        return DishesRequest.builder().build();
    }


    public static IngredientsRequest getIngredientsServiceRequest() {
        return IngredientsRequest.builder()
                .ingredientId(1)
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
