package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.service.PlayerService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/v1/playerservice")
@CrossOrigin
public class MainController {

	
	@Autowired
	PlayerService playerService;
	
	
	@PostMapping("/player")
	public ResponseEntity<?> savePlayer(@RequestBody Player player,final HttpServletRequest request,  
			final HttpServletResponse response){
		final String autheHeader = request.getHeader("authorization");
		final String token = autheHeader.substring(7);	
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		try {
			player.setUserId(userId);
			playerService.savePlayer(player, userId);
			return new ResponseEntity<Player>(player,HttpStatus.CREATED);
		}catch(PlayerAlreadyExistsException e) {
			return new ResponseEntity("message:"+e.getMessage(),HttpStatus.CONFLICT);
		}
		}
	
	@DeleteMapping("player/{id}")
	public ResponseEntity<?> deletePlayerById(@PathVariable("id") final int id){
		try {
			playerService.deletePlayerById(id);
		return new ResponseEntity<String>("message: Player Deleted Successfully",HttpStatus.OK);
		}catch(PlayerNotFoundException e) {
			return new ResponseEntity<String>("message:"+e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("player/{id}")
	public ResponseEntity<?> getPlayerById(@PathVariable("id") final int id){
		try {
			Player player = playerService.getPlayerById(id);
			return new ResponseEntity(player,HttpStatus.OK);
		}catch(PlayerNotFoundException e) {
			return new ResponseEntity("message:"+e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	

	
	@GetMapping("/player")
	public ResponseEntity<?> getMyPlayer(final HttpServletRequest request,HttpServletResponse response){
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			List<Player> playerList = playerService.getMyPlayers(userId);
		ResponseEntity<?> responseEntity = new ResponseEntity<List<Player>>(playerList,HttpStatus.OK);
		return responseEntity;
	}
}
