package com.example.demo.pojo;

public enum EnumTest {
    test1("1岁", 1),
    test2("2岁", 2),
    test3("3岁", 3),
    test4("4岁", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private EnumTest(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (EnumTest c : EnumTest.values()) {
            if (c.getIndex() == index) {
                return EnumTest.getNEXT(index+1);
            }
        }
        return null;
    }
    // 普通方法
    public static String getNEXT(int index) {
        for (EnumTest c : EnumTest.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
