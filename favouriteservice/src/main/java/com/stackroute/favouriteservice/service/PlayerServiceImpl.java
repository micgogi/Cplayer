package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepo;
	@Override
	public boolean savePlayer(Player player, String userId) throws PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		
		Optional<Player> optional = playerRepo.findByNameAndUserId(player.getName(), userId);
		if(optional.isPresent()) {
			throw new PlayerAlreadyExistsException("Player alreday Exists");
		}
		playerRepo.save(player);
		return true;
	}

	@Override
	public boolean deletePlayerById(int id) throws PlayerNotFoundException {
		// TODO Auto-generated method stub
		Player player = playerRepo.findById(id);
		if(player == null)
			throw new PlayerNotFoundException("Player Not Found");
		playerRepo.delete(player);
		return true;
	}

	@Override
	public Player getPlayerById(int id) throws PlayerNotFoundException {
		// TODO Auto-generated method stub
		Player player = playerRepo.findById(id);
		if(player==null)
			throw new PlayerNotFoundException("Player Not Found");
		return player;
	}

	@Override
	public List<Player> getMyPlayers(String userId) {
		// TODO Auto-generated method stub
		return playerRepo.findByUserId(userId);
	}

}
