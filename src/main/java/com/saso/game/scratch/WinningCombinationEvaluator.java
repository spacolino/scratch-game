package com.saso.game.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saso.game.scratch.config.GameConfig;
import com.saso.game.scratch.config.WinCombinationConfig;

public class WinningCombinationEvaluator {
    private final GameConfig config;

    public WinningCombinationEvaluator(GameConfig config) {
        this.config = config;
    }

    public Map<String, List<String>> evaluateWinningCombinations(String[][] matrix) {
        Map<String, List<String>> winningCombinations = new HashMap<>();

        for (Map.Entry<String, WinCombinationConfig> entry : config.winCombinations().entrySet()) {
            String combinationName = entry.getKey();
            WinCombinationConfig combinationConfig = entry.getValue();

            switch (combinationConfig.when()) {
                case "same_symbols":
                    for (int i = 0; i < config.rows(); i++) {
                        for (int j = 0; j < config.columns(); j++) {
                            String symbol = matrix[i][j];
                            if (symbol != null && !symbol.isEmpty() && config.symbols().get(symbol).type().equals("standard")) {
                                int count = countSameSymbols(matrix, symbol);
                                if (count >= combinationConfig.count()) {
                                    winningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>()).add(combinationName);
                                }
                            }
                        }
                    }
                    break;
                case "linear_symbols":
                    for (List<String> coveredArea : combinationConfig.coveredAreas()) {
                        String symbol = matrix[coveredArea.get(0).charAt(0) - '0'][coveredArea.get(0).charAt(2) - '0'];
                        boolean match = true;
                        for (String pos : coveredArea) {
                            int row = pos.charAt(0) - '0';
                            int col = pos.charAt(2) - '0';
                            if (!matrix[row][col].equals(symbol)) {
                                match = false;
                                break;
                            }
                        }
                        if (match && config.symbols().get(symbol).type().equals("standard")) {
                            winningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>()).add(combinationName);
                        }
                    }
                    break;
            }
        }

        return winningCombinations;
    }

    private int countSameSymbols(String[][] matrix, String symbol) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(symbol)) {
                    count++;
                }
            }
        }
        return count;
    }
}
