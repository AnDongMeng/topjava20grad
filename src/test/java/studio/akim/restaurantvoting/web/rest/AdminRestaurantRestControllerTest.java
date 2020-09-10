package studio.akim.restaurantvoting.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import studio.akim.restaurantvoting.TestData.FoodTestData;
import studio.akim.restaurantvoting.TestData.RestaurantTestData;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.repository.FoodRepository;
import studio.akim.restaurantvoting.repository.RestaurantRepository;
import studio.akim.restaurantvoting.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static studio.akim.restaurantvoting.TestData.FoodTestData.*;
import static studio.akim.restaurantvoting.TestData.RestaurantTestData.*;
import static studio.akim.restaurantvoting.TestData.UserTestData.ADMIN;
import static studio.akim.restaurantvoting.TestData.UserTestData.USER;
import static studio.akim.restaurantvoting.TestUtil.readFromJson;
import static studio.akim.restaurantvoting.TestUtil.userHttpBasic;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestController.REST_URL + '/';

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void putRestaurant() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(newId).orElse(null), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(getUpdated().id()).orElse(null), updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RSTRNT1_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertThat(restaurantRepository.findById(RSTRNT1_ID).orElse(null)).isNull();
    }

    @Test
    void checkAuth() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RSTRNT1_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void putFood() throws Exception {
        Food newFood = FoodTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RSTRNT1_ID + "/food")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newFood)))
                .andExpect(status().isCreated());

        Food created = readFromJson(action, Food.class);
        int newId = created.id();
        newFood.setId(newId);
        FOOD_MATCHER_WITHOUT_RESTAURANT.assertMatch(created, newFood);
        FOOD_MATCHER.assertMatch(foodRepository.getWithRestaurant(newId), newFood);
    }

    @Test
    void deleteFood() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "food/" + RSTRNT1_TODAY_FOOD_ID1).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertThat(foodRepository.findById(RSTRNT1_TODAY_FOOD_ID1).orElse(null)).isNull();
    }
}