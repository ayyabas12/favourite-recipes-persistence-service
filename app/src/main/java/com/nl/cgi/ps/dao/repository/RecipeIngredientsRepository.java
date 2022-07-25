package com.nl.cgi.ps.dao.repository;

import com.nl.cgi.ps.dao.model.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {


    @Query(value = "SELECT INGREDIENTS_ID, from TBL_RECIPE_INGREDIENTS where RECIPE_ID  =:RecipeId", nativeQuery = true)
    public List<Long> getAllIngredientsByRecipeID(@Param("RecipeId") long recipeId);

    @Query(value = "SELECT INGREDIENTS_ID, from TBL_RECIPE_INGREDIENTS where RECIPE_ID IN (:RecipeId)", nativeQuery = true)
    public List<Long> getAllIngredientsByRecipeID(@Param("RecipeId") List<Long> RecipeId);

}
