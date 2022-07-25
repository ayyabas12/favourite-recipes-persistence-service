package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.model.Ingredients;
import com.nl.cgi.ps.dao.repository.IngredientsRepository;
import com.nl.cgi.ps.model.request.IngredientsRequest;
import com.nl.cgi.ps.model.response.IngredientsResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class IngredientsPersistenceService {

    private final IngredientsRepository ingredientsRepository;

    /**
     * @return
     */
    public IngredientsResponse getIngredientsDetails() {
        return IngredientsResponse.builder().ingredients(ingredientsRepository.findAll()).build();
    }

    /**
     * @param request
     */
    public void saveIngredientsDetails(IngredientsRequest request) {
        var ingredients = ingredientsRepository.findById(request.getIngredientId());
        log.debug(" Recipes details is not found and save the Recipes{}", request.getIngredientName());
        if (ingredients.isPresent()) {
            Ingredients details = ingredients.get();
            details.setIngredientName(request.getIngredientName());
            details.setImageurl(request.getImageURL());
            ingredientsRepository.save(details);
            log.debug("Recipes details saved successfully {}", request.getIngredientName());
        } else {
            ingredientsRepository.save(buildIngredientsDetails(request));
            log.debug("Recipes details saved successfully {}", request.getIngredientId());
        }
    }

    /**
     * @param request
     * @return
     */
    private Ingredients buildIngredientsDetails(IngredientsRequest request) {
        return Ingredients.builder()
                .ingredientId(request.getIngredientId() != 0 ? request.getIngredientId() : 0)
                .ingredientName(request.getIngredientName())
                .imageurl(request.getImageURL())
                .build();
    }


}
