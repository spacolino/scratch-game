package com.saso.game.scratch.config;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameConfig(
	int columns,
	int rows,
	Map<String, SymbolConfig> symbols,
    ProbabilitiesConfig probabilities,
    
    @JsonProperty("win_combinations")
    Map<String, WinCombinationConfig> winCombinations
) {}
