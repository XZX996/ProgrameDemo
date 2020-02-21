package com.company;

import javax.lang.model.element.VariableElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

            int[] myArray = {1, 2, 3, 4, 5};
            ChangeIt.doIt(myArray);
            for (int i = 0; i < myArray.length; i++)
            {
                System.out.print(myArray[i] + " ");
            }
            int a=20;
            ChangeIt.one(a);
            System.out.println(a);
            ArrayList<Integer> str=new ArrayList<Integer>(Arrays.asList(12,3231,31));
           /* str.forEach(e->{
                System.out.println(e);
             });*/
           str.stream().filter(e->(e%2==0)).toArray();
           System.out.println(str);
	// write your code here
 /*       final hero gareen = new hero();
        gareen.name = "盖伦";
        gareen.hp = 10000;

        int n = 10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {

                //使用gareen作为synchronized
               // synchronized (gareen) {
                    gareen.recover();
                // }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            t.start();
            addThreads[i] = t;

        }

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {
                //使用gareen作为synchronized
                //在方法hurt中有synchronized(this)
                gareen.hurt();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            t.start();
            reduceThreads[i] = t;
        }

        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量是 %.0f%n", n,n,gareen.hp);*/
        Process p = Runtime.getRuntime().exec("ping " + "11.101.2.204");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
        }
        System.out.println("本次指令返回的消息是：");
        System.out.println(sb.toString());
    }
}
class ChangeIt
{
    static void doIt( int[] z )
    {
        int[] A = z;
        A[0] = 99;
    }

    static void  one(int a){
        a=10;

    }

}

