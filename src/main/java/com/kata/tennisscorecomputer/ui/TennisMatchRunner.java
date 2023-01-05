package com.kata.tennisscorecomputer.ui;

import com.kata.tennisscorecomputer.domain.models.Player;
import com.kata.tennisscorecomputer.domain.ports.ScoreComputerService;
import com.kata.tennisscorecomputer.domain.ports.ScoreDisplayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TennisMatchRunner {
    private Player firstPlayer = new Player("A");
    private Player secondPlayer = new Player("B");

    @Autowired
    ScoreComputerService scoreComputer;

    @Autowired
    ScoreDisplayerService scoreDisplayer;

    public void evaluateMatchScore(String points) {
        points.chars()
                .forEach(name -> {
                    Player scoringPlayer = getPlayerByName(String.valueOf((char) name));
                    scoreComputer.scorePoint(scoringPlayer);
                    scoreDisplayer.getGameScore(scoreComputer.getCurrentScore());
                });

        scoreDisplayer.getPartyScore(scoreComputer.getCurrentScore());
    }

    private Player getPlayerByName(String name) throws IllegalArgumentException {
        switch (name) {
            case "A":
                return firstPlayer;
            case "B":
                return secondPlayer;
            default:
                throw new IllegalArgumentException("the given name is not allowed");
        }
    }
}
