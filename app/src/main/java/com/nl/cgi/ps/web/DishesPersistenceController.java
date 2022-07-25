package com.nl.cgi.ps.web;

import com.nl.cgi.ps.model.request.DishesRequest;
import com.nl.cgi.ps.model.response.DishesResponse;
import com.nl.cgi.ps.service.DishesPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dishes-persistence-service/recipes-details")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class DishesPersistenceController {

    private final DishesPersistenceService dishesPersistenceService;

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<DishesResponse> getRecipesDetails() {
        log.info("Inside request Dishes dishes call");
        DishesResponse response = dishesPersistenceService.getDishesDetails();
        return ResponseEntity.ok(response);
    }

    /**
     * @param dishesRequest
     * @return
     */
    @PutMapping
    public ResponseEntity<Boolean> saveOrUpdateDish(@RequestBody DishesRequest dishesRequest) {
        log.info("Inside search or update dishes method call");
        dishesPersistenceService.saveDishDetails(dishesRequest);
        return ResponseEntity.ok(true);
    }

    /**
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteRecipesDetails(@RequestParam(name = "dishId") Long dishId) {
        log.info("Inside delete dishes method call");
        dishesPersistenceService.deleteRecipes(dishId);
        return ResponseEntity.ok(true);
    }


}
