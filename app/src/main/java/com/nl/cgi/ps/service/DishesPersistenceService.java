package com.nl.cgi.ps.service;


import com.nl.cgi.ps.dao.model.Dishes;
import com.nl.cgi.ps.dao.repository.DishesRepository;
import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.response.DishesResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class DishesPersistenceService {

    private final DishesRepository dishesRepository;

    /**
     * @return
     */
    public DishesResponse getDishesDetails() {
        return DishesResponse.builder().dishesNames(dishesRepository.findAll()).build();
    }


    /**
     * @param request
     */
    public void saveDishDetails(DishesRequest request) {
        var dishes = dishesRepository.findById(request.getDishId());
        log.debug(" Recipes details is not found and save the Recipes{}", request.getDishName());
        if (dishes.isPresent()) {
            Dishes details = dishes.get();
            details.setDishName(request.getDishName());
            details.setCategory(request.getCategory());
            details.setInstructions(request.getInstructions());
            dishesRepository.save(details);
            log.debug("Recipes details saved successfully {}", request.getDishName());
        } else {
            dishesRepository.save(buildRecipesDetails(request));
            log.debug("Recipes details saved successfully {}", request.getDishId());
        }
    }

    /**
     * @param dishId
     */
    public void deleteRecipes(Long dishId) {
        dishesRepository.deleteById(dishId);
    }

    /**
     * @param request
     * @return
     */
    private Dishes buildRecipesDetails(DishesRequest request) {
        return Dishes.builder().
                dishName(request.getDishName())
                .category(request.getCategory())
                .instructions(StringUtils.isNotEmpty(request.getInstructions()) ? request.getInstructions().strip() : "")
                .quantity(request.getQuantity())
                .build();
    }


}
