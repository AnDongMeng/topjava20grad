package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.service.DataService;

import javax.validation.Valid;
import java.net.URI;

import static studio.akim.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static studio.akim.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private DataService dataService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> save(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = dataService.createRestaurant(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        dataService.updateRestaurant(restaurant);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        dataService.deleteRestaurant(id);
    }

    @PostMapping(value = "/{id}/food", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Food> saveFood(@Valid @RequestBody Food food, @PathVariable int id) {
        checkNew(food);
        Food created = dataService.createFood(food, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/food/{foodId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Food> updateFood(@Valid @RequestBody Food food, @PathVariable int id, @PathVariable int foodId) {
        assureIdConsistent(food, foodId);

        dataService.updateFood(food, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/food/{foodId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable int id, @PathVariable int foodId) {
        dataService.deleteFood(id, foodId);
    }

}
