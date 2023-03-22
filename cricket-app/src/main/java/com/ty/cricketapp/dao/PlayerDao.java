package com.ty.cricketapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.cricketapp.dto.Player;
import com.ty.cricketapp.repository.PlayerRepository;

@Repository
public class PlayerDao {

	@Autowired
	private PlayerRepository playerRepository;

	public Player savePlayer(Player newPlayer) {
		return playerRepository.save(newPlayer);
	}

	public Player searchPlayer(int playerId) {
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isEmpty()) {
			return null;
		} else {
			return playerOptional.get();
		}
	}

	public Player updatePlayer(Player player) {
		return playerRepository.save(player);
	}

	public Player deletePlayer(Player searchedPlayer) {
		playerRepository.delete(searchedPlayer);
		return searchedPlayer;
	}

	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	public List<Player> findByName(String name) {
		return playerRepository.findByName(name);

	}

	public List<Player> findByRunsBetween(double minRuns, double maxRuns) {
		
		return playerRepository.findByRunsBetween(minRuns, maxRuns);
	}
	
	public 	List<Player> findByAverageGreaterThan(double minAvg){
		return playerRepository.findByAverageGreaterThan(minAvg);
		
	}
	
}
