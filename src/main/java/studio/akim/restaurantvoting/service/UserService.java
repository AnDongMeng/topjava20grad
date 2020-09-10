package studio.akim.restaurantvoting.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import studio.akim.restaurantvoting.AuthorizedUser;
import studio.akim.restaurantvoting.model.User;
import studio.akim.restaurantvoting.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.getByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
