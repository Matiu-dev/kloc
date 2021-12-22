package pl.uwb.kloc.controller.user;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.uwb.kloc.model.User;
import pl.uwb.kloc.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableWebMvc
public class AddUser {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser",
            method = POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus addUser(HttpEntity<String> httpEntity) {

        String json = httpEntity.getBody();
        Gson gson = new Gson();
        User userGson = gson.fromJson(json, User.class);
        userService.saveUser(userGson);

        return HttpStatus.OK;
    }
}