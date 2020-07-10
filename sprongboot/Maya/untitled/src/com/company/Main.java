package com.company;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.lang.model.element.VariableElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static Object pr(){
        Object o=null;
        try{
           o="1";
           return o;
        }catch (Exception e){
            o="2";
            return o;
        }finally {
            o="3";
        }
    }
    static  int arr[]=new int[5];
    public static void main(String[] args) throws IOException {

       int ii=1;int j;
       j=ii++*2+3*++ii;
       System.out.println(ii);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(pr());
        superC a1ww=new superC();

        superC b=new superB();
        superB c=new superB();

        String ww=new String("11");
        String bb=new String("1")+new String("1");
        System.out.println(ww);
        System.out.println(bb);
        System.out.println(ww==bb);

        double dd=9.0;
        //float ff=1.0;
       // String ee='3.0';

        System.out.println(a1ww.a+b.a+c.a);
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

            List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    // 获取空字符串的数量
            long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
            System.out.println(count);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
         sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        List<String> a1= new ArrayList<>(Arrays.asList("5","6","7"));
        List<String> a2=Arrays.asList("5","6","7");
        a1.addAll(a2);
        a1=a1.stream().distinct().collect(Collectors.toList());
        System.out.println(a1);
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

