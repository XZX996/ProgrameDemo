package com.company.CreateType.Builder;

public class OneProduct {
    private String partA;
    private String partB;
    private String partC;
    public void setPartA(String partA)
    {
        this.partA=partA;
    }
    public void setPartB(String partB)
    {
        this.partB=partB;
    }
    public void setPartC(String partC)
    {
        this.partC=partC;
    }
    public void show()
    {
        System.out.println(String.format("该产品由：%s，%s，%s，构成",this.partA,this.partB,this.partC));
    }
}
