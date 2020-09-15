package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import studio.akim.restaurantvoting.model.Restaurant;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.repository.RestaurantRepository;
import studio.akim.restaurantvoting.repository.VoteRepository;
import studio.akim.restaurantvoting.util.DateTimeUtil;
import studio.akim.restaurantvoting.util.ValidationUtil;
import studio.akim.restaurantvoting.util.exception.LateVotingException;
import studio.akim.restaurantvoting.web.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return ValidationUtil.checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }


    @GetMapping
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithTodaysFood(@PathVariable int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithDaysFood(id, DateTimeUtil.currentDate()), id);
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithTodaysFood() {
        return repository.getAllWithDaysFood(DateTimeUtil.currentDate());
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id) {
        Vote curVote = voteRepository.getVoteByUserAndDate(SecurityUtil.authUserId(), DateTimeUtil.currentDate());
        Vote vote = new Vote(null, SecurityUtil.safeGet().getUser(), get(id), DateTimeUtil.currentDate());
        if (curVote == null) {
            voteRepository.save(vote);
        } else if (DateTimeUtil.userCanChangeVote()) {
            vote.setId(curVote.getId());
            voteRepository.save(vote);
        } else {
            throw new LateVotingException();
        }
    }
}
