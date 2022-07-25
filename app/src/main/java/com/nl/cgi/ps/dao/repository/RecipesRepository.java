package com.nl.cgi.ps.dao.repository;

import com.nl.cgi.ps.dao.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipesRepository extends JpaRepository<Recipes, Long> {

 @Query(value = "SELECT * from TBL_RECIPES  where CATEGORY =:category OR QUANTITY >=:quantity OR  INSTRUCTIONS LIKE  %:instructions%" , nativeQuery = true)
    public List<Recipes> filterFavouriteFoodRecipesByFilterValue(@Param("category") String category, @Param("quantity") long quantity, @Param("instructions") String instructions);

}
