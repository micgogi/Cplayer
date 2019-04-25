package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.PlayerRepository;


public class PlayerServiceTest {
	@Mock
	private transient PlayerRepository playerRepository;

	private transient Player  player;

	@InjectMocks
	private transient PlayerServiceImpl playerServiceImpl;
	transient Optional<Player> options;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		player = new Player(1, 2, "110", "abc","abc");
		options = Optional.of(player);
	}

	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on ServiceImpl", player);
	}

	@Test
	public void testSavePlayerSuccess() throws PlayerAlreadyExistsException {
		when(playerRepository.save(player)).thenReturn(player);
		final boolean flag = playerServiceImpl.savePlayer(player, "110");
		assertTrue("saving player failed", flag);
		verify(playerRepository, times(1)).save(player);
	}
	@Test
	public void testDeletePlayerById() throws PlayerNotFoundException {
		when(playerRepository.findById(1)).thenReturn(player);
		doNothing().when(playerRepository).delete(player);
		final boolean flag = playerServiceImpl.deletePlayerById(1);
		assertTrue("deleting movie failed", flag);
		verify(playerRepository, times(1)).delete(player);
		verify(playerRepository, times(1)).findById(player.getId());
	}

	@Test
	public void testGetAllPlayeres() throws PlayerNotFoundException {
		final List<Player> players = new ArrayList<>();
		players.add(player);
		when(playerRepository.findByUserId(player.getUserId())).thenReturn(players);
		final List<Player> players1 = playerServiceImpl.getMyPlayers("110");
		assertEquals(players, players1);
		verify(playerRepository, times(1)).findByUserId("110");
	}


}
