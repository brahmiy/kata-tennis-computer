package com.kata.tennisscorecomputer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kata.tennisscorecomputer.domain.models.Player;
import com.kata.tennisscorecomputer.domain.models.Score;
import com.kata.tennisscorecomputer.domain.ports.ScoreDisplayerService;

import org.springframework.stereotype.Component;

@Component
public class ScoreDisplayerServiceImpl implements ScoreDisplayerService {
    public String getGameScore(Map<Player, Score> scoreByPlayer) {
        List<StringBuilder> builders = new ArrayList<>();

        scoreByPlayer.keySet().forEach((Player player) -> {
            StringBuilder builder = new StringBuilder();
            builder.append("Player ");
            builder.append(player.getName());
            builder.append(" : ");
            builder.append(scoreByPlayer.get(player).getCurrentPoint().value);

            builders.add(builder);
        });

        return builders.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.joining(" / "));
    }

    public String getPartyScore(Map<Player, Score> scoreByPlayer) {
        List<StringBuilder> builders = new ArrayList<>();

        scoreByPlayer.keySet().forEach((Player player) -> {
            StringBuilder builder = new StringBuilder();
            builder.append("Player ");
            builder.append(player.getName());
            builder.append(" : ");
            scoreByPlayer.get(player).getPointsBySet()
                    .keySet()
                    .forEach((Integer set) -> {
                        builder.append(String.format("[SET %d] %d", set.intValue(),
                                scoreByPlayer.get(player).getPointsBySet().get(set).intValue()));
                    });
            builders.add(builder);
        });

        return builders.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.joining(" / "));
    }
}
