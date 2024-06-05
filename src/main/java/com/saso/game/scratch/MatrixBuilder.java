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

	        // Calculate total probability for standard symbols
	        int totalStandardProbability = config.probabilities().standardSymbols().stream()
	                .flatMap(prob -> prob.symbols().values().stream())
	                .mapToInt(Integer::intValue).sum();

	        // Calculate total probability for bonus symbols
	        int totalBonusProbability = config.probabilities().bonusSymbols().symbols().values().stream()
	                .mapToInt(Integer::intValue).sum();

	        // Fill matrix with symbols based on probabilities
	        for (int i = 0; i < config.rows(); i++) {
	            for (int j = 0; j < config.columns(); j++) {
	                int randomValue = random.nextInt(totalStandardProbability + totalBonusProbability) + 1;
	                int cumulativeProbability = 0;

	                // Check standard symbols
	                boolean placed = false;
	                for (StandardSymbolProbability prob : config.probabilities().standardSymbols()) {
	                    for (Map.Entry<String, Integer> entry : prob.symbols().entrySet()) {
	                        cumulativeProbability += entry.getValue();
	                        if (randomValue <= cumulativeProbability) {
	                            matrix[i][j] = entry.getKey();
	                            placed = true;
	                            break;
	                        }
	                    }
	                    if (placed) break;
	                }

	                // Check bonus symbols if not already filled
	                if (!placed) {
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
