package com.saso.game.scratch;

import java.util.Map;
import java.util.Random;

import com.saso.game.scratch.config.GameConfig;
import com.saso.game.scratch.config.StandardSymbolProbability;

public class MatrixBuilder {
	    private final GameConfig config;
	    private final Random random;

	    public MatrixBuilder(GameConfig config) {
	        this.config = config;
	        this.random = new Random();
	    }

	    public String[][] generateMatrix() {
	        String[][] matrix = new String[config.rows()][config.columns()];

	        // Fill matrix with standard symbols based on probabilities
	        for (StandardSymbolProbability prob : config.probabilities().standardSymbols()) {
	            int totalProbability = prob.symbols().values().stream().mapToInt(Integer::intValue).sum();
	            int randomValue = random.nextInt(totalProbability) + 1;
	            int cumulativeProbability = 0;
	            for (Map.Entry<String, Integer> entry : prob.symbols().entrySet()) {
	                cumulativeProbability += entry.getValue();
	                if (randomValue <= cumulativeProbability) {
	                    matrix[prob.row()][prob.column()] = entry.getKey();
	                    break;
	                }
	            }
	        }

	        // Randomly place bonus symbols in the matrix
	        for (int i = 0; i < config.rows(); i++) {
	            for (int j = 0; j < config.columns(); j++) {
	                if (matrix[i][j] == null) {
	                    int totalProbability = config.probabilities().bonusSymbols().symbols().values().stream().mapToInt(Integer::intValue).sum();
	                    int randomValue = random.nextInt(totalProbability) + 1;
	                    int cumulativeProbability = 0;
	                    for (Map.Entry<String, Integer> entry : config.probabilities().bonusSymbols().symbols().entrySet()) {
	                        cumulativeProbability += entry.getValue();
	                        if (randomValue <= cumulativeProbability) {
	                            matrix[i][j] = entry.getKey();
	                            break;
	                        }
	                    }
	                }
	            }
	        }

	        return matrix;
	    }
}
