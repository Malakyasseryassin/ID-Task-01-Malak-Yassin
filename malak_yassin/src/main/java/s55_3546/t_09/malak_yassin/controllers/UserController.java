package s55_3546.t_09.malak_yassin.controllers;

import org.springframework.web.bind.annotation.*;

import s55_3546.t_09.malak_yassin.models.User;
import s55_3546.t_09.malak_yassin.models.Note;
import s55_3546.t_09.malak_yassin.services.UserService;
import s55_3546.t_09.malak_yassin.services.NoteService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final NoteService noteService;
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/{id}/notes")
    public List<Note> getUserNotes(@PathVariable String id) {
        return noteService.getNotesByUserId(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id,
                           @RequestBody User user) throws Exception {

        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) throws Exception {
        userService.deleteUser(id);
    }
    @GetMapping("/search")
    public User searchByUsername(@RequestParam String username){
        return userService.getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                org.springframework.http.HttpStatus.NOT_FOUND));
    }

}
