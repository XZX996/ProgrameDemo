package com.company.CreateType.Builder;

public class Maintest {

    public static void main(String[] args)
    {
        OneBuilder builder=new ConcreteBuilder();
        Director director=new Director(builder);
        OneProduct product=director.construct();
        product.show();
    }

}
