package com.ty.cricketapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.cricketapp.dto.Player;
import com.ty.cricketapp.response.ResponseStructure;
import com.ty.cricketapp.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	// @RequestMapping(path = "/save-player", method = RequestMethod.POST)
	@PostMapping("/save-player")
	public ResponseEntity<ResponseStructure<Player>> savePlayer(@RequestBody Player newPlayer) {
		Player savedPlayer = playerService.savePlayer(newPlayer);
		ResponseStructure<Player> responseStructure = new ResponseStructure<>();
		if (savedPlayer != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Player Saved");
			responseStructure.setData(savedPlayer);

			return new ResponseEntity<ResponseStructure<Player>>(responseStructure, HttpStatus.CREATED);
		} else {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Player NOT saved");
			responseStructure.setData(null);

			return new ResponseEntity<ResponseStructure<Player>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	// @RequestMapping(path = "/search-player/{id}")
	@GetMapping("/search-player/{id}")
	public ResponseEntity<ResponseStructure<Player>> getPlayer(@PathVariable("id") int playerId) {
		Player player = playerService.searchPlayer(playerId);
		ResponseStructure<Player> responseStructure = new ResponseStructure<>();
		if (player != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Player found");
			responseStructure.setData(player);

			return new ResponseEntity<ResponseStructure<Player>>(responseStructure, HttpStatus.FOUND);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Player not found with id " + playerId);
			responseStructure.setData(null);

			return new ResponseEntity<ResponseStructure<Player>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update-player")
	public ResponseEntity<ResponseStructure<Player>> updatePlayer(@RequestBody Player player) {
		Player searchedPlayer = playerService.searchPlayer(player.getId());
		if (searchedPlayer != null) {
			// update
			Player updatedPlayer = playerService.updatePlayer(player);
			if (updatedPlayer != null) {
				ResponseStructure<Player> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("update done");
				responseStructure.setData(updatedPlayer);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			} else {
				ResponseStructure<Player> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("update cannot be done");
				responseStructure.setData(null);
				return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
			}
		} else {
			ResponseStructure<Player> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("update cannot be done");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete-player/{id}")
	public ResponseEntity<ResponseStructure<Player>> deletePlayer(@PathVariable("id") int playerId) {
		Player searchedPlayer = playerService.searchPlayer(playerId);
		if (searchedPlayer != null) {
			// delete
			Player deletedPlayer = playerService.deletePlayer(searchedPlayer);
			if (deletedPlayer != null) {
				ResponseStructure<Player> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Delete done");
				responseStructure.setData(deletedPlayer);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			} else {
				ResponseStructure<Player> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("delete cannot be done");
				responseStructure.setData(null);
				return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
			}
		} else {
			ResponseStructure<Player> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("delete cannot be done");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/players")
	public ResponseEntity<ResponseStructure<List<Player>>> getAllPlayers() {
		List<Player> players = playerService.getAllPlayers();

		ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All Players");
		responseStructure.setData(players);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@GetMapping("/search-player-name/{name}")
	public ResponseEntity<ResponseStructure<List<Player>>> findByName(@PathVariable("name") String name) {

		List<Player> gotName = playerService.findByName(name);
		if (!gotName.isEmpty()) {

			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("GET BY NAME");
			responseStructure.setData(gotName);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Name Not Found");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
	}

	@GetMapping("/search-player-runs")
	public ResponseEntity<ResponseStructure<List<Player>>> findByRunsBetween(@RequestParam("minRuns") double minRuns,
			@RequestParam("maxRuns") double maxRuns) {

		List<Player> gotByRuns = playerService.findByRunsBetween(minRuns, maxRuns);
		if (!gotByRuns.isEmpty()) {

			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("GET BY RUNS");
			responseStructure.setData(gotByRuns);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("NOT FOUND BEWTWEEN RUNS");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
	}

	@GetMapping("/search-player-avg-more/{minAvg}")
	public ResponseEntity<ResponseStructure<List<Player>>> findByAverageMoreThan(
			@PathVariable("minAvg") double minAvg) {
		List<Player> gotByAvgMoreThan = playerService.findByAverageGreaterThan(minAvg);
		if (!gotByAvgMoreThan.isEmpty()) {

			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("GET MORE THAN " + minAvg);
			responseStructure.setData(gotByAvgMoreThan);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			ResponseStructure<List<Player>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("NOT FOUND MORE THAN" + minAvg);
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

}
