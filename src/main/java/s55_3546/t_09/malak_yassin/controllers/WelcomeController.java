package s55_3546.t_09.malak_yassin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${app.description}")
    private String description;

    @Value("${app.version}")
    private String version;

    @Value("${USER_NAME}")
    private String userName;

    @Value("${ID}")
    private String id;

    @GetMapping("/welcome")
    public String welcome() {
        return description + " v" + version + " - " + userName + " (" + id + ")";
    }
}