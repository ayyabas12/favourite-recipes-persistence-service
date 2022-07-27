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
    public IngredientsResponse getIngredientsDetails(final long id) {
        var ingredient = ingredientsRepository.findById(id);
        if (ingredient !=null && ingredient.isPresent()) {
            return buildIngredientsResponse(ingredient.get());
        }
        return IngredientsResponse.builder().build();
    }

    /**
     * @param request ingredientsRequest
     */
    public IngredientsResponse saveIngredient(final IngredientsRequest request) {
        Ingredients ingredient = ingredientsRepository.save(buildIngredientsRequest(request));
        log.debug("Recipes details saved successfully {}", ingredient.getIngredientsId());
        return buildIngredientsResponse(ingredient);
    }

    /**
     *
     * @param id of ingredient
     * @param request of ingredient
     * @return update ingredient
     */
    public IngredientsResponse updateIngredient(final long id, final IngredientsRequest request) {
        var ingredients = ingredientsRepository.findById(id);
        log.debug(" Recipes details is found and update the Recipes{}", request.getIngredientName());
        if (ingredients.isPresent()) {
            Ingredients details = ingredients.get();
            details.setIngredientName(request.getIngredientName());
            details.setImageurl(request.getImageURL());
            Ingredients ingredient = ingredientsRepository.save(details);
            log.debug("Recipes details saved successfully {}", request.getIngredientName());
            return buildIngredientsResponse(ingredient);
        } else {
            log.debug("Recipes is not found {}", id);
            return IngredientsResponse.builder().build();
        }
    }

    /**
     * @param request build ingredient
     * @return ingredient
     */
    private Ingredients buildIngredientsRequest(IngredientsRequest request) {
        return Ingredients.builder()
                .ingredientName(request.getIngredientName())
                .imageurl(request.getImageURL())
                .build();
    }

    private IngredientsResponse buildIngredientsResponse(Ingredients response) {
        return IngredientsResponse.builder()
                .ingredients(response)
                .build();
    }


}
