package com.company.structural.filter;

import java.util.List;

/**
 * 抽象过滤器，也可以用抽象类，但是面向接口思维
 */
public  interface MyfiLter {
    //返回过滤后的对象
    public List<Person> meetFilter(List<Person> persons);
}
