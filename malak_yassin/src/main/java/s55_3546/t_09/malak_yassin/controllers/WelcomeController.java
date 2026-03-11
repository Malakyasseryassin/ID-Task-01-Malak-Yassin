package s55_3546.t_09.malak_yassin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {

    @Value("${USER_NAME}")
    private String userName;

    @Value("${ID}")
    private String id;

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello " + userName + " " + id + ", from Notes API";
    }
}
