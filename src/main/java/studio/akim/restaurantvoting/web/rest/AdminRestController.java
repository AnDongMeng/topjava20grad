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
import studio.akim.restaurantvoting.repository.FoodRepository;
import studio.akim.restaurantvoting.repository.RestaurantRepository;

import javax.validation.Valid;
import java.net.URI;

import static studio.akim.restaurantvoting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> save(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(restaurantRepository.save(restaurant), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @PostMapping(value = "/{id}/food", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Food> saveFood(@Valid @RequestBody Food food, @PathVariable int id) {
        checkNew(food);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);

        food.setRestaurant(restaurant);
        Food created = foodRepository.save(food);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/food/{foodId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Food> updateFood(@Valid @RequestBody Food food, @PathVariable int id, @PathVariable int foodId) {
        assureIdConsistent(food, foodId);

        checkNotFoundWithId(foodRepository.getWithRestaurant(foodId).filter(food1 -> food1.getRestaurant().getId() == id).orElse(null), foodId);
        Restaurant restaurant = restaurantRepository.getOne(id);
        food.setRestaurant(restaurant);

        checkNotFoundWithId(foodRepository.save(food), foodId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/food/{foodId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable int id, @PathVariable int foodId) {
        checkNotFoundWithId(foodRepository.delete(id, foodId) != 0, foodId);
    }

}
