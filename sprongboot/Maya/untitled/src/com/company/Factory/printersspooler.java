package com.company.Factory;

/**
 * 懒汉单例
 */
public class printersspooler{
    private static volatile printersspooler instance=null;
    private printersspooler(){
        //do something
    }
    public static  printersspooler getInstance(){
        if(instance==null){
            synchronized(printersspooler.class){
                if(instance==null){
                    instance=new printersspooler();
                }
            }
        }
        return instance;
    }
}
