package com.saso.game.scratch;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.saso.game.scratch.config.GameConfig;

public class RewardCalculator {
    private final GameConfig config;

    @Inject
    public RewardCalculator(GameConfig config) {
        this.config = config;
    }

    public double calculateReward(double betAmount, Map<String, List<String>> winningCombinations) {
        double reward = 0;

        for (Map.Entry<String, List<String>> entry : winningCombinations.entrySet()) {
            String symbol = entry.getKey();
            List<String> combinations = entry.getValue();

            double symbolReward = betAmount * config.symbols().get(symbol).rewardMultiplier();

            for (String combination : combinations) {
                symbolReward *= config.winCombinations().get(combination).rewardMultiplier();
            }

            reward += symbolReward;
        }

        return reward;
    }
}
