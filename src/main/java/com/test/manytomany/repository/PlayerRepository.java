package com.test.manytomany.repository;

import com.test.manytomany.model.Board;
import com.test.manytomany.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends Repository<Player, Long> {

    @Query(value = "SELECT * FROM player WHERE login = :login", nativeQuery = true)
    Optional<Player> findByLoginTwo(@Param("login") String login);



    Player findByid(Long boardId);

    Player findByLogin(String login);

    Player save(Player player);

}
