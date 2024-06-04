package com.saso.game.scratch;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.saso.game.scratch.config.GameConfig;
import com.saso.game.scratch.config.SymbolConfig;

public class BonusApplier {
    private final GameConfig config;

    @Inject
    public BonusApplier(GameConfig config) {
        this.config = config;
    }

    public double applyBonusSymbols(String[][] matrix, double reward, Map<String, List<String>> winningCombinations) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String symbol = matrix[i][j];
                if (symbol != null && !symbol.isEmpty() && config.symbols().get(symbol).type().equals("bonus")) {
                    SymbolConfig bonusConfig = config.symbols().get(symbol);
                    switch (bonusConfig.impact()) {
                        case "multiply_reward":
                            reward *= bonusConfig.rewardMultiplier();
                            break;
                        case "extra_bonus":
                            reward += bonusConfig.extra();
                            break;
                        case "miss":
                            break;
                    }
                }
            }
        }
        return reward;
    }
}
