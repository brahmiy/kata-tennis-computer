package com.kata.tennisscorecomputer.domain.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Score {
    private int currentSet = 1;
    private int currentGame;
    private Point currentPoint = Point.ZERO;

    private Map<Integer, Integer> pointsBySet = new HashMap<>();

    public GameStatus addPoint(GameStatus status) {
        switch (this.currentPoint) {
            case ZERO:
                this.currentPoint = Point.FIFTEEN;
                break;
            case FIFTEEN:
                this.currentPoint = Point.THIRTY;
                break;
            case THIRTY:
                this.currentPoint = Point.FORTY;
                break;
            case FORTY:
                if (status == GameStatus.DEUCE) {
                    this.currentPoint = Point.ADVANTAGE;
                } else {
                    status = this.winGame();
                }
                break;
            case ADVANTAGE:
                status = this.winGame();
                break;
        }

        this.updateScore();
        return status;
    }

    private void updateScore() {
        this.pointsBySet.put(this.currentSet, this.currentGame);
    }

    public GameStatus winGame() {
        if (this.currentGame < 6) {
            this.currentGame++;
            return GameStatus.GAME_WON;
        } else {
            // need here to add more rules of winning set
            return this.winSet();
        }
    }

    public GameStatus winSet() {
        if (this.currentSet == 2) {
            boolean firstSetWon = pointsBySet.get(Integer.valueOf(1)) == 6;
            return firstSetWon ? GameStatus.MATCH_WON : GameStatus.SET_WON;
        } else if (this.currentSet == 3) {
            return GameStatus.MATCH_WON;
        }
        return GameStatus.SET_WON;
    }

    public void loseAdvantage() {
        this.currentPoint = Point.FORTY;
        this.updateScore();
    }

    public void startNewSet() {
        if (this.currentSet < 3) {
            this.currentSet++;
            this.pointsBySet.put(this.currentSet, 0);
        }
    }

    public void startNewGame() {
        this.currentPoint = Point.ZERO;
    }
}
