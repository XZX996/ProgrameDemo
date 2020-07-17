package com.company.structural.adapter;

/**
 * 适配器接口
 */
public class ClassAdpterC extends adapterC implements targetclass {

    @Override
    public void request() {
        specificRequest();
    }
}
