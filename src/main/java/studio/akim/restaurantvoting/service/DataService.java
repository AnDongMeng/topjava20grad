package studio.akim.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.repository.FoodRepository;
import studio.akim.restaurantvoting.repository.RestaurantRepository;
import studio.akim.restaurantvoting.repository.VoteRepository;
import studio.akim.restaurantvoting.util.DateTimeUtil;
import studio.akim.restaurantvoting.util.exception.VotingException;
import studio.akim.restaurantvoting.web.SecurityUtil;

import java.util.List;

import static studio.akim.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DataService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private VoteRepository voteRepository;

    public Restaurant getRestaurant(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public Restaurant getRestaurantWithTodaysMenu(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithDaysMenu(id, DateTimeUtil.currentDate()), id);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Cacheable("restaurantsWithMenu")
    public List<Restaurant> getAllRestaurantsWithTodaysMenu() {
        return restaurantRepository.getAllWithDaysMenu(DateTimeUtil.currentDate());
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public void deleteRestaurant(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public Food createFood(Food food, int restaurantId) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById(restaurantId).orElse(null), restaurantId);
        food.setRestaurant(restaurant);
        return foodRepository.save(food);
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public Food updateFood(Food food, int restaurantId) {
        checkNotFoundWithId(foodRepository.getWithRestaurant(food.getId()).filter(food1 -> food1.getRestaurant().getId() == restaurantId).orElse(null), food.getId());
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        food.setRestaurant(restaurant);

        return checkNotFoundWithId(foodRepository.save(food), food.getId());
    }

    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public void deleteFood(int id, int foodId) {
        checkNotFoundWithId(foodRepository.delete(id, foodId) != 0, foodId);
    }

    public Vote createVote(Vote vote) {
        if (voteRepository.getVoteByUserAndDate(SecurityUtil.authUserId(), DateTimeUtil.currentDate()) != null)
            throw new VotingException("user has already voted");
        return voteRepository.save(vote);
    }

    public Vote updateVote(Vote vote) {
        Vote curVote = voteRepository.getVoteByUserAndDate(SecurityUtil.authUserId(), DateTimeUtil.currentDate());
        if (curVote == null) {
            throw new VotingException("user hasn't voted yet");
        } else if (DateTimeUtil.userCanChangeVote()) {
            vote.setId(curVote.getId());
            return voteRepository.save(vote);
        } else {
            throw new VotingException("User cant vote again after 11:00");
        }
    }
}
