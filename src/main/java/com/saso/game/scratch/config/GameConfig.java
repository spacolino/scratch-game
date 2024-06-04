package com.saso.game.scratch.config;

import java.util.Map;

public record GameConfig(
	int columns,
	int rows,
	Map<String, SymbolConfig> symbols,
    ProbabilitiesConfig probabilities,
    Map<String, WinCombinationConfig> win_combinations
) {}
