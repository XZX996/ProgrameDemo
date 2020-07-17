package com.company.structural.filter;

import java.util.ArrayList;
import java.util.List;

public class OrFilter implements MyfiLter {

    private MyfiLter criteria;
    private MyfiLter otherCriteria;

    public OrFilter(MyfiLter criteria, MyfiLter otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }
    @Override
    public List<Person> meetFilter(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetFilter(persons);
        List<Person> otherCriteriaItems = otherCriteria.meetFilter(persons);

        for (Person person : otherCriteriaItems) {
            if(!firstCriteriaItems.contains(person)){
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
