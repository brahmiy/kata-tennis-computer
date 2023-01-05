package com.kata.tennisscorecomputer.domain.ports;

import java.util.Map;

import com.kata.tennisscorecomputer.domain.models.Player;
import com.kata.tennisscorecomputer.domain.models.Score;

public interface ScoreDisplayerService {
    public String getGameScore(Map<Player, Score> scoreByPlayer);
    public String getPartyScore(Map<Player, Score> scoreByPlayer);
}
