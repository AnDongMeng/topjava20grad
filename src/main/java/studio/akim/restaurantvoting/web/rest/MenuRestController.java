package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.repository.FoodRepository;

import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/admin/menus";

    @Autowired
    private FoodRepository repository;

    @GetMapping
    public List<Food> getAll() {
        return repository.findAll();
    }

}
