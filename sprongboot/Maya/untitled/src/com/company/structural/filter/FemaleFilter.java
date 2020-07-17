package com.company.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤 生成女性类，低耦合
 */
public class FemaleFilter implements MyfiLter {

    @Override
    public List<Person> meetFilter(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
