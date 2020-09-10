package studio.akim.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.akim.restaurantvoting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByName(String name);
}
