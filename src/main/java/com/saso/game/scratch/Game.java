package com.saso.game.scratch;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.saso.game.scratch.config.GameConfig;

public class Game {
	public static void main(String[] args) {
		if (args.length < 2) {
            System.out.println("Usage: java -jar <game-jar-file> --config <game-config-file> --betting-amount <amount>");
            return;
        }
		
		// we get config file path and betting amount from command line parameters
		String configFile = args[1];
		double betAmount = Double.parseDouble(args[3]);
		
		try {
			
			// read game config
			ObjectMapper objectMapper = new ObjectMapper();
			GameConfig config = objectMapper.readValue(new File(configFile), GameConfig.class);
			
            MatrixBuilder matrixGenerator = new MatrixBuilder(config);
            String[][] matrix = matrixGenerator.generateMatrix();

            WinningCombinationEvaluator evaluator = new WinningCombinationEvaluator(config);
            Map<String, List<String>> winningCombinations = evaluator.evaluateWinningCombinations(matrix);

            RewardCalculator rewardCalculator = new RewardCalculator(config);
            double reward = rewardCalculator.calculateReward(betAmount, winningCombinations);

            BonusApplier bonusApplier = new BonusApplier(config);
            reward = bonusApplier.applyBonusSymbols(matrix, reward, winningCombinations);

            String appliedBonusSymbol = getAppliedBonusSymbol(matrix, config);

            Result result = new Result(matrix, reward, winningCombinations, appliedBonusSymbol);

            // Convert the result to JSON
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println(jsonString);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getAppliedBonusSymbol(String[][] matrix, GameConfig config) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String symbol = matrix[i][j];
                if (symbol != null && config.symbols().containsKey(symbol) && "bonus".equals(config.symbols().get(symbol).type())) {
                    return symbol;
                }
            }
        }
        return null;
    }
}
