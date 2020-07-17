package com.company.structural.proxy;

public class proxyclass implements subject {
    private  subject Sub;

    public proxyclass(subject sub){
        this.Sub=sub;
    }

    @Override
    public void request() {

        preRequest();
        Sub.request();
        postRequest();

    }


    public void preRequest()
    {
        System.out.println("访问真实主题之前的预处理。");
    }
    public void postRequest()
    {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
