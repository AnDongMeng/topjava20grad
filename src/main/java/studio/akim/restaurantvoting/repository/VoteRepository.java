package studio.akim.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import studio.akim.restaurantvoting.model.Vote;

import java.time.LocalDate;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = ?1 AND v.date = ?2")
    Vote getVoteByUserAndDate(int user_id, LocalDate date);
}
