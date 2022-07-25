package com.nl.cgi.ps.web;

import com.nl.cgi.ps.model.request.IngredientsRequest;
import com.nl.cgi.ps.model.response.IngredientsResponse;
import com.nl.cgi.ps.service.IngredientsPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
@RequestMapping("/ingredients-persistence-service/ingredients-details")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuperBuilder(toBuilder = true)
public class IngredientsPersistenceController {

    private final IngredientsPersistenceService ingredientsPersistenceService;

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<IngredientsResponse> getIngredientsDetail(final @RequestParam(name = "id") long id) {
        log.info("Inside request Ingredients method call");
        IngredientsResponse response = ingredientsPersistenceService.getIngredientsDetails(id);
        return ResponseEntity.ok(response);
    }

    /**
     * @param recipesRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<IngredientsResponse> saveIngredient(@RequestBody IngredientsRequest recipesRequest) {
        log.info("Inside save Ingredients method call");
        return ResponseEntity.ok(ingredientsPersistenceService.saveIngredient(recipesRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientsResponse> UpdateIngredient(@PathVariable("id") long id, @RequestBody IngredientsRequest recipesRequest) {
        log.info("Inside update Ingredients method call");
        return ResponseEntity.ok(ingredientsPersistenceService.updateIngredient(id, recipesRequest));
    }
}
