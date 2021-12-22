package pl.uwb.kloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uwb.kloc.model.User;
import pl.uwb.kloc.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User userGson) {
        User user = userRepository.findById(userGson.getUserId());
        user.setLogin(userGson.getLogin());
        user.setPassword(userGson.getPassword());
        user.setUserrole(userGson.getUserrole());
        saveUser(user);
    }

}