package s55_3546.t_09.malak_yassin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import s55_3546.t_09.malak_yassin.models.User;
import s55_3546.t_09.malak_yassin.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User createUser(User user) throws Exception {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) throws Exception {

        return userRepository.update(id, user)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteUser(String id) throws Exception {

        boolean deleted = userRepository.deleteById(id);

        if(!deleted)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public User getUserByUsername(String username) {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}