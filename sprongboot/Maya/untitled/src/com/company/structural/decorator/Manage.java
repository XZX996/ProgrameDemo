package com.company.structural.decorator;

import com.company.structural.filter.Person;

public class Manage implements person {
    protected person pe;

    @Override
    public void doCoding() {
        pe.doCoding();
    }
}
