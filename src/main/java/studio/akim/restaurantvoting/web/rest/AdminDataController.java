package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.model.User;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.repository.FoodRepository;
import studio.akim.restaurantvoting.repository.UserRepository;
import studio.akim.restaurantvoting.repository.VoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = AdminDataController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDataController {
    static final String REST_URL = "/rest/admin/data";

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/votes")
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/menus")
    public List<Food> getAllMenus() {
        return foodRepository.findAll();
    }
}
