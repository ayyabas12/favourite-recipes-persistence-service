package com.nl.cgi.ps.dao.repository;

import com.nl.cgi.ps.dao.model.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {


    @Query(value = "SELECT INGREDIENTS_ID, from TBL_RECIPE_INGREDIENTS where RECIPE_ID  =:RecipeId", nativeQuery = true)
    List<Long> getAllIngredientsByRecipeID(@Param("RecipeId") long recipeId);

    @Modifying
    @Query(value = "delete from TBL_RECIPE_INGREDIENTS where RECIPE_ID =:RecipeId", nativeQuery = true)
    public void deleteByRecipeId(@Param("RecipeId") long recipeId);

}
