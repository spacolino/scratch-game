package com.saso.game.scratch;

import com.google.inject.AbstractModule;
import com.saso.game.scratch.config.GameConfig;

public class ScratchGameModule extends AbstractModule {
    private final GameConfig config;

    public ScratchGameModule(GameConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(GameConfig.class).toInstance(config);
        bind(MatrixBuilder.class);
        bind(WinningCombinationEvaluator.class);
        bind(RewardCalculator.class);
        bind(BonusApplier.class);
    }
}
