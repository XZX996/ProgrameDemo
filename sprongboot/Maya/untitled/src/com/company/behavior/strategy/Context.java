package com.company.behavior.strategy;

/**
 * 上下文，使用策略
 */
public class Context {
    private AbsStrategy strategy;
    public AbsStrategy getStrategy(){
        return strategy;
    }
    public void setStrategy(AbsStrategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.OneMethod();
    }


}
