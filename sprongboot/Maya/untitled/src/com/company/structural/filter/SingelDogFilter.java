package com.company.structural.filter;

import java.util.ArrayList;
import java.util.List;

public class SingelDogFilter implements MyfiLter {

    @Override
    public List<Person> meetFilter(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
