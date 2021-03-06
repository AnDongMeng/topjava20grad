package studio.akim.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import studio.akim.restaurantvoting.model.Food;

import java.util.Optional;

@Transactional(readOnly = true)
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Food f WHERE f.id = :food_id AND f.restaurant.id = :id")
    int delete(@Param("id") int id, @Param("food_id") int foodId);

    @Query("SELECT f FROM Food f JOIN FETCH f.restaurant  WHERE f.id = ?1 ")
    Optional<Food> getWithRestaurant(int id);
}
