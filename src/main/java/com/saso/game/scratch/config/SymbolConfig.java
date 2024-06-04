package com.saso.game.scratch.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SymbolConfig(
		@JsonProperty("reward_multiplier")
		double rewardMultiplier,		
		String type,		
		double extra,		
		String impact
) {}
