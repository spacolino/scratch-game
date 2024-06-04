package com.saso.game.scratch.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WinCombinationConfig(
		
		@JsonProperty("reward_multiplier")
		double rewardMultiplier,
		
        String when,
        
        Integer count,
        
        String group,
        
        @JsonProperty("covered_areas")
        List<List<String>> coveredAreas
) {}
