package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.repository.RestaurantRepository;
import studio.akim.restaurantvoting.repository.VoteRepository;
import studio.akim.restaurantvoting.util.DateTimeUtil;
import studio.akim.restaurantvoting.util.ValidationUtil;
import studio.akim.restaurantvoting.util.exception.VotingException;
import studio.akim.restaurantvoting.web.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }


    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithTodaysFood(@PathVariable int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.getWithDaysMenu(id, DateTimeUtil.currentDate()), id);
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithTodaysFood() {
        return restaurantRepository.getAllWithDaysMenu(DateTimeUtil.currentDate());
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public void vote(@PathVariable int id) {
        if (voteRepository.getVoteByUserAndDate(SecurityUtil.authUserId(), DateTimeUtil.currentDate()) != null)
            throw new VotingException("user has already voted");
        Vote vote = new Vote(null, SecurityUtil.safeGet().getUser(), get(id), DateTimeUtil.currentDate());
        voteRepository.save(vote);
    }

    @PutMapping("/{id}/vote")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void revote(@PathVariable int id) {
        Vote curVote = voteRepository.getVoteByUserAndDate(SecurityUtil.authUserId(), DateTimeUtil.currentDate());
        Vote vote = new Vote(null, SecurityUtil.safeGet().getUser(), get(id), DateTimeUtil.currentDate());
        if (curVote == null) {
            throw new VotingException("user hasn't voted yet");
        } else if (DateTimeUtil.userCanChangeVote()) {
            vote.setId(curVote.getId());
            voteRepository.save(vote);
        } else {
            throw new VotingException("User cant vote again after 11:00");
        }
    }
}
