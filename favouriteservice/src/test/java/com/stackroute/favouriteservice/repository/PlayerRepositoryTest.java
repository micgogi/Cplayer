package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Player;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PlayerRepositoryTest {
	@Autowired
	private transient PlayerRepository playerRepository;

	public void setRepo(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Test
	public void testSavePlayer() throws Exception {
		playerRepository.save(new Player(1, 2, "110", "abc","abc"));
		final Player player = playerRepository.findById(1);
		System.out.println(player);
		assertThat(player.getName()).isEqualTo("abc");
	}
	@Test
	public void testGetMyPlayers() throws Exception {
		playerRepository.save(new Player(1, 2, "110", "abc","abc"));;
		playerRepository.save(new Player(1, 2, "110", "abc","abc"));
		final List<Player> players = playerRepository.findByUserId("110");
		assertEquals("abc", players.get(0).getName());
	}



}
