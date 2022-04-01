package com.test.manytomany.repository;

import com.test.manytomany.model.player.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PlayerRepository extends Repository<Player, Long> {

    @Query(value = "SELECT * FROM player WHERE login = :login", nativeQuery = true)
    Optional<Player> findByLoginTwo(@Param("login") String login);

    Player findByid(Long boardId);

    Player findByLogin(String login);

    Player save(Player player);

    List<Player> findAll();

}
