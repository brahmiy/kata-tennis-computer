package com.kata.tennisscorecomputer;

import com.kata.tennisscorecomputer.ui.TennisMatchRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisScoreComputerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TennisScoreComputerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TennisMatchRunner runner = new TennisMatchRunner();
		if (args.length > 0) {
			runner.evaluateMatchScore(args[0]);	
		}
	}

}
