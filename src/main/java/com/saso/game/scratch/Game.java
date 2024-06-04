package com.saso.game.scratch;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			
			// read game config from json
			ObjectMapper objectMapper = new ObjectMapper();
			GameConfig config = objectMapper.readValue(new File(configFile), GameConfig.class);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
