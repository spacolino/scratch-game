package com.saso.game.scratch.config;

import java.util.Map;

public record StandardSymbolProbability(
		int column,
        int row,
        Map<String, Integer> symbols
) {}
