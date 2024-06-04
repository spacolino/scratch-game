package com.saso.game.scratch.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProbabilitiesConfig(
		 @JsonProperty("standard_symbols")
		 List<StandardSymbolProbability> standardSymbols,
		 
		 @JsonProperty("bonus_symbols")
	     BonusSymbolProbability bonusSymbols
) {}
