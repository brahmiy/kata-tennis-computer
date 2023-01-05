package com.kata.tennisscorecomputer.domain.ports;

import java.util.Map;

import com.kata.tennisscorecomputer.domain.models.Player;
import com.kata.tennisscorecomputer.domain.models.Score;

public interface ScoreComputerService {
    public void scorePoint(Player winner);
    public Map<Player, Score> getCurrentScore();
}
