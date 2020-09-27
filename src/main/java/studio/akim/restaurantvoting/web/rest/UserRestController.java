package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.service.DataService;
import studio.akim.restaurantvoting.util.DateTimeUtil;
import studio.akim.restaurantvoting.web.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private DataService dataService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return dataService.getRestaurant(id);
    }


    @GetMapping
    public List<Restaurant> getAll() {
        return dataService.getAllRestaurants();
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithTodaysFood(@PathVariable int id) {
        return dataService.getRestaurantWithTodaysMenu(id);
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithTodaysFood() {
        return dataService.getAllRestaurantsWithTodaysMenu();
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public void vote(@PathVariable int id) {
        Vote vote = new Vote(null, SecurityUtil.safeGet().getUser(), get(id), DateTimeUtil.currentDate());
        dataService.createVote(vote);
    }

    @PutMapping("/{id}/vote")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void revote(@PathVariable int id) {
        Vote vote = new Vote(null, SecurityUtil.safeGet().getUser(), get(id), DateTimeUtil.currentDate());
        dataService.updateVote(vote);
    }
}
