package com.ty.cricketapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players_table")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String team;
	private String role;
	private int runs;
	private double average;
	@Column(name = "jersy_number")
	private int jersyNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getJersyNumber() {
		return jersyNumber;
	}

	public void setJersyNumber(int jersyNumber) {
		this.jersyNumber = jersyNumber;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", team=" + team + ", role=" + role + ", runs=" + runs
				+ ", average=" + average + ", jersyNumber=" + jersyNumber + "]";
	}

}
