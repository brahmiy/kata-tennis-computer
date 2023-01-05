package com.kata.tennisscorecomputer.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.kata.tennisscorecomputer.domain.models.GameStatus;
import com.kata.tennisscorecomputer.domain.models.Player;
import com.kata.tennisscorecomputer.domain.models.Point;
import com.kata.tennisscorecomputer.domain.models.Score;
import com.kata.tennisscorecomputer.domain.ports.ScoreComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ScoreComputerServiceImpl implements ScoreComputerService {
    private Map<Player, Score> scoreByPlayer;
    private GameStatus status = GameStatus.PLAYING;

    @Autowired
    public ScoreComputerServiceImpl(Player firstPlayer, Player secondPlayer) {
        this.scoreByPlayer = new HashMap<>();
        this.scoreByPlayer.put(firstPlayer, new Score());
        this.scoreByPlayer.put(secondPlayer, new Score());
    }

    public void scorePoint(Player winner) {
        Player secondPlayer = getSecondPlayer(winner);

        Score currentFirstPlayerScore = scoreByPlayer.get(winner);
        Score currentSecondPlayerScore = scoreByPlayer.get(secondPlayer);

        if (currentSecondPlayerScore.getCurrentPoint() == Point.ADVANTAGE) {
            currentSecondPlayerScore.loseAdvantage();
        } else {
            boolean isDeuce = currentFirstPlayerScore.getCurrentPoint() == Point.FORTY
                    && currentSecondPlayerScore.getCurrentPoint() == Point.FORTY;
            status = isDeuce ? GameStatus.DEUCE : status;
            status = currentFirstPlayerScore.addPoint(status);
        }

        switch (status) {
            case GAME_WON:
                currentFirstPlayerScore.startNewGame();
                currentSecondPlayerScore.startNewGame();
                status = GameStatus.PLAYING;
                break;
            case SET_WON:
                currentFirstPlayerScore.startNewSet();
                currentSecondPlayerScore.startNewSet();
                currentFirstPlayerScore.startNewGame();
                currentSecondPlayerScore.startNewGame();
                status = GameStatus.PLAYING;
                break;
            default:
                break;
        }

        if (status == GameStatus.GAME_WON) {
            currentFirstPlayerScore.winGame();
            currentFirstPlayerScore.startNewGame();
            currentSecondPlayerScore.startNewGame();

            status = GameStatus.PLAYING;
        }

        scoreByPlayer.replace(winner, currentFirstPlayerScore);
        scoreByPlayer.replace(secondPlayer, currentSecondPlayerScore);
    }

    public Map<Player, Score> getCurrentScore() {
        return this.scoreByPlayer;
    }

    private Player getSecondPlayer(Player firstPlayer) {
        Set<Player> players = scoreByPlayer.keySet();
        Player secondPlayer = null;
        for (Player player : players) {
            if (!player.equals(firstPlayer)) {
                secondPlayer = player;
                break;
            }
        }
        return secondPlayer;
    }
}
