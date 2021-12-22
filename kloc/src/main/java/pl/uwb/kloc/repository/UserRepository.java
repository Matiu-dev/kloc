package pl.uwb.kloc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import pl.uwb.kloc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

    @Query(value = "SELECT * FROM kloc_user WHERE login = :login", nativeQuery = true)
    Optional<User> findByLoginTwo(@Param("login") String login);

    Optional<User> findByLogin(String login);

    List<User> findAll();

    //add
    User save(User user);

    //delete
    void deleteById(String id);

    //update
    User findById(String id);
}
