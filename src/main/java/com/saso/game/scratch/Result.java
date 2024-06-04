package com.saso.game.scratch;

import java.util.List;
import java.util.Map;

public record Result(
	String[][] matrix,
    double reward,
    Map<String, List<String>> appliedWinningCombinations,
    String appliedBonusSymbol
) {}
