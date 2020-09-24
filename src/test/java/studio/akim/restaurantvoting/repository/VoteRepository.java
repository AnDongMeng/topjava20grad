package studio.akim.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import studio.akim.restaurantvoting.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant JOIN FETCH v.user WHERE v.user.id = ?1 AND v.date = ?2")
    Vote getVoteByUserAndDate(int userId, LocalDate date);
}
