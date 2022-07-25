package com.nl.cgi.ps.dao.repository;

import com.nl.cgi.ps.dao.model.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {

    @Query(value = "SELECT TRI.ID ,TD.DISH_ID,TD.DISH_NAME,TD.INSTRUCTIONS ,TD.QUANTITY,TRI.INGREDIENT_ID, TI.INGREDIENT_NAME,TI.IMAGEURL    FROM TBL_DISHES TD , TBL_RECIPE_INGREDIENTS TRI, TBL_INGREDIENTS TI  WHERE TD.CATEGORY = :category  AND  TD.QUANTITY >=:quantity  AND  TD.DISH_ID=TRI.DISH_ID AND TRI.INGREDIENT_ID IN (:ingredientId) AND TI.INGREDIENT_ID= TRI.INGREDIENT_ID", nativeQuery = true)
    public List<RecipeIngredients> filterFavouriteFoodRecipesByFilterValue(@Param("category") String category, @Param("quantity") long quantity, @Param("ingredientId") List<Long> ingredientId);

    @Query(value = "SELECT INGREDIENT_ID from TBL_RECIPE_INGREDIENTS where DISH_ID  =:DishId", nativeQuery = true)
    public List<Long> getAllIngredientsByRecipeID(@Param("DishId") long dishId);

    @Query(value = "  SELECT TRI.ID ,TD.DISH_ID,TD.DISH_NAME,TD.INSTRUCTIONS ,TD.QUANTITY,TRI.INGREDIENT_ID, TI.INGREDIENT_NAME,TI.IMAGEURL FROM TBL_DISHES TD , TBL_RECIPE_INGREDIENTS TRI, TBL_INGREDIENTS TI  WHERE   TD.DISH_ID=TRI.DISH_ID  AND TI.INGREDIENT_ID= TRI.INGREDIENT_ID  AND TD.INSTRUCTIONS  LIKE  %:instructions%" , nativeQuery = true)
    public List<RecipeIngredients> filterFavouriteFoodRecipesByFilterInstructions(@Param("instructions") String instructions);

}
