package com.test.manytomany;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerRole;
import com.test.manytomany.repository.PlayerRepository;
import com.test.manytomany.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@RunWith(SpringRunner.class)
public class IntegrationPlayerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRepository playerService;

    @Test
    public void createPlayer() {
        //given
        Player player = new Player();
        player.setLogin("test1");
        player.setPassword("pass");
        player.setPlayerRole(PlayerRole.ROLE_USER);

        entityManager.persist(player);
        entityManager.flush();

        //when
        Player found = playerService.findByLogin(player.getLogin());

        //then
        assertThat(found.getLogin()).isEqualTo(player.getLogin());
    }

    @Test
    public void getPlayer() {

    }


    private static String loadJsonFile(final String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
