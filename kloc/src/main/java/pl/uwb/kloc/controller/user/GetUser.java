package pl.uwb.kloc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.uwb.kloc.model.User;
import pl.uwb.kloc.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@EnableWebMvc
public class GetUser {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers",
            method = GET,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/getUser/{login}",
            method = GET,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<User> getUser(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }

}
