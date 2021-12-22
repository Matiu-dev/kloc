package pl.uwb.kloc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.uwb.kloc.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@EnableWebMvc
public class DeleteUser {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/deleteUser/{id}",
            method = DELETE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus deleteById(@PathVariable("id") String id) {

        userService.deleteById(id);

        return HttpStatus.OK;
    }
}