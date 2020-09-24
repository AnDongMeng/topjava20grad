package studio.akim.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import studio.akim.restaurantvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menu f WHERE f.date = ?2 and r.id = ?1 ")
    Restaurant getWithDaysMenu(int id, LocalDate date);


    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menu f  ON f.date = ?1 ORDER BY r.id")
    List<Restaurant> getAllWithDaysMenu(LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE  FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
