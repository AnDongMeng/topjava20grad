package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.repository.FoodRepository;
import studio.akim.restaurantvoting.repository.RestaurantRepository;
import studio.akim.restaurantvoting.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {

    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> save(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = null;
        created = repository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@Valid @RequestBody Restaurant restaurant) {

        repository.save(restaurant);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @PostMapping(value = "/{restaurant_id}/food", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> saveFood(@PathVariable int restaurant_id, @Valid @RequestBody Food food) {
        Restaurant restaurant = repository.findById(restaurant_id).orElse(null);
        food.setRestaurant(restaurant);
        food.setDate(LocalDate.now());
        Food created;
        try {
            created = foodRepository.save(food);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Food is not unique");
        }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/food/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable int id) {
        ValidationUtil.checkNotFoundWithId(foodRepository.delete(id) != 0, id);
    }

}
