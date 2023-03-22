package com.ty.cricketapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.cricketapp.dao.PlayerDao;
import com.ty.cricketapp.dto.Player;

@Service
public class PlayerService {

	@Autowired
	private PlayerDao playerDao;

	public Player savePlayer(Player newPlayer) {
		return playerDao.savePlayer(newPlayer);
	}

	public Player searchPlayer(int playerId) {
		return playerDao.searchPlayer(playerId);
	}

	public Player updatePlayer(Player player) {
		return playerDao.updatePlayer(player);
	}

	public Player deletePlayer(Player searchedPlayer) {
		return playerDao.deletePlayer(searchedPlayer);
	}

	public List<Player> getAllPlayers() {
		return playerDao.getAllPlayers();
	}

	public List<Player> findByName(String name) {
		return playerDao.findByName(name);

	}

	public List<Player> findByRunsBetween(double minRuns, double maxRuns) {

		return playerDao.findByRunsBetween(minRuns, maxRuns);
	}

	public List<Player> findByAverageGreaterThan(double minAvg) {
		return playerDao.findByAverageGreaterThan(minAvg);

	}

}
