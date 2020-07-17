package com.company.structural.decorator;

public class ManageA extends Manage {
    public ManageA(person pe) {
        this.pe = pe;
    }

    @Override
    public void doCoding() {
        doEarly();
        super.doCoding();
    }

    private void doEarly() {
        System.out.println("项目经理A处理前期事项");
    }
}
