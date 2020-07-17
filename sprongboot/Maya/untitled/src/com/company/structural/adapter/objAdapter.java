package com.company.structural.adapter;

/**
 * 对象适配器
 */
public class objAdapter implements targetclass {

    private  adapterC ada;
    public objAdapter(adapterC ada){
        this.ada=ada;
    }

    @Override
    public void request() {
        ada.specificRequest();
    }

}
