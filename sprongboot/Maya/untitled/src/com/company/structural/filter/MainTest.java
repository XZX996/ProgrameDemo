package com.company.structural.filter;

import com.company.structural.compose.transparentModel.Component;
import com.company.structural.compose.transparentModel.Composite;
import com.company.structural.compose.transparentModel.leaf;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args)
    {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("张三","Male", "Single"));
        persons.add(new Person("张四","Male", "Married"));
        persons.add(new Person("李武","Female", "Married"));
        persons.add(new Person("赵六","Female", "Single"));
        persons.add(new Person("王琦","Male", "Single"));
        persons.add(new Person("四千","Male", "Single"));

        MyfiLter male = new MaleFilter();
        MyfiLter female = new FemaleFilter();
        MyfiLter single = new SingelDogFilter();
        MyfiLter singleOrFemale = new OrFilter(single, female);

        System.out.println("Males: ");
        printPersons(male.meetFilter(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetFilter(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetFilter(persons));
    }
    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }
}
