package com.stackroute.favouriteservice.controller;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.service.PlayerService;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class PlayerControllerTest {

	

	@Autowired
	private transient MockMvc mvc;

	@MockBean
	private transient PlayerService playerService;

	private transient Player player;

	@InjectMocks
	private MainController mainController;

	static List<Player> players;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders.standaloneSetup(mainController).build();
		players = new ArrayList<>();
		player = new Player(1, 2, "110", "abc","abc");
		players.add(player);
		player = new Player(1, 3, "110", "abc","abc");
		players.add(player);
	}


	
	@Test
	public void testSaveNewPlayerSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTAiLCJpYXQiOjE1NTI5NTU3ODR9.MjBjfqrQUH09d0074OTVpja-MQnHAfvsrJwfvW1PBJM";

		when(playerService.savePlayer(player, "110")).thenReturn(true);
		mvc.perform(post("/api/v1/playerservice/player").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(player))).andExpect(status().isCreated());
		verify(playerService, times(1)).savePlayer(Mockito.any(Player.class), org.mockito.Mockito.eq("110"));
		verifyNoMoreInteractions(playerService);
	}
	@Test
	public void testNotSaveFailure() throws Exception{
		when(playerService.savePlayer(any(Player.class), any(String.class))).thenThrow(new PlayerAlreadyExistsException("Player Already Exist"));
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTAiLCJpYXQiOjE1NTI5NTU3ODR9.MjBjfqrQUH09d0074OTVpja-MQnHAfvsrJwfvW1PBJM";

	//when(playerService.savePlayer(player, "110")).thenThrow(new PlayerAlreadyExistsException("Player already Exist"));
		
	mvc.perform(post("/api/v1/playerservice/player").header("authorization", "Bearer " + token)
			.contentType(MediaType.APPLICATION_JSON).content(jsonToString(player))).andExpect(status().isConflict());
	verify(playerService, times(1)).savePlayer(Mockito.any(Player.class),Mockito.any(String.class));
	verifyNoMoreInteractions(playerService);
	}

	@Test
	public void testDeletePlayerById() throws Exception {
		when(playerService.deletePlayerById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/playerservice/player/{id}", 1)).andExpect(status().isOk());
		verify(playerService, times(1)).deletePlayerById(1);
		verifyNoMoreInteractions(playerService);
	}
	

	@Test
	public void testDeletePlayerByIdFailure() throws Exception {
		when(playerService.deletePlayerById(256)).thenThrow(new PlayerNotFoundException("Player Not Found"));
		mvc.perform(delete("/api/v1/playerservice/player/{id}",256)).andExpect(status().isNotFound());
		verify(playerService, times(1)).deletePlayerById(256);
		verifyNoMoreInteractions(playerService);
	}
	
	@Test
	public void testGetMyPlayers() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTAiLCJpYXQiOjE1NTI5NTU3ODR9.MjBjfqrQUH09d0074OTVpja-MQnHAfvsrJwfvW1PBJM";
		when(playerService.getMyPlayers(("110"))).thenReturn(null);
		mvc.perform(get("/api/v1/playerservice/player").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(playerService, times(1)).getMyPlayers("110");
		verifyNoMoreInteractions(playerService);
	}
	

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
