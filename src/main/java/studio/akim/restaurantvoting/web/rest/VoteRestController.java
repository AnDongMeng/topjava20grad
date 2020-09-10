package studio.akim.restaurantvoting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.akim.restaurantvoting.model.Vote;
import studio.akim.restaurantvoting.repository.VoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/admin/votes";

    @Autowired
    private VoteRepository repository;

    @GetMapping
    public List<Vote> getAll() {
        return repository.findAll();
    }
}
