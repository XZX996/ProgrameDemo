package com.company;

import jdk.nashorn.internal.runtime.Undefined;
import sun.util.resources.CurrencyNames;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThread {
    static class HurtThread extends Thread{
        private hero1 hero;

        public HurtThread(hero1 hero){
            this.hero = hero;
        }

        public void run(){
            while(true){
                hero.hurt();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    static class RecoverThread extends Thread{
        private hero1 hero;

        public RecoverThread(hero1 hero){
            this.hero = hero;
        }

        public void run(){
            while(true){
                hero.recover();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    
    public static void main(String[] args) {
        final hero1 gareen = new hero1();
    /*    gareen.name = "盖伦";
        gareen.hp = 616;

        Thread t1 = new Thread(){
            public void run(){
                while(true){

                    //无需循环判断
//                    while(gareen.hp==1){
//                        continue;
//                    }

                    gareen.hurt();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();

        Thread t2 = new Thread(){
            public void run(){
                while(true){
                    gareen.recover();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };
        t2.start();*/

        final hero1 gareen1 = new hero1();
        gareen1.name = "盖伦";
        gareen1.hp = 616;
/*
        for (int i = 0; i < 2; i++) {
            new RecoverThread(gareen1).start();
        }
        for (int i = 0; i < 5; i++) {
            new HurtThread(gareen1).start();
        }*/
        /**自定义线程池*/
        /*ThreadPool pool = new ThreadPool();

        for (int i = 0; i < 15; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务");
                    //任务可能是打印一句话
                    //可能是访问文件
                    //可能是做排序
                }
            };

            pool.add(task);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/


        ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        threadPool.execute(new Runnable(){

            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    // TODO Auto-generated method stub
                    System.out.println("任务:");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}
