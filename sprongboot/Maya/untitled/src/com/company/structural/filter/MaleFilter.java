package com.company.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤 生成男性类，低耦合
 */
public class MaleFilter implements MyfiLter {

    @Override
    public List<Person> meetFilter(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
