package com.ty.cricketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.cricketapp.dto.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

	List<Player> findByName(String name);
	List<Player> findByRunsBetween(double minRuns,double maxRuns);

	List<Player> findByAverageGreaterThan(double minAvg);

}
