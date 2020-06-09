package com.company;

import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InterMain {
    static Integer sub;
    static Integer re;

    static String sub1;
    static String sub2;
    static String re1;
    static int[] a={1,2,3,4,5,6,7,8,9,11,99,13,14,15,16,17,12,19,10,18};

    private static Integer FF(int n){
        if(n<=1){
            return 1;
        }else{
            sub=FF(n-1);
            re=sub*n;
            return re;
        }
    }

    private static int bser(int valu){
        //Arrays.binarySearch()
       // HashMap
        int low=0;
        int mid=0;
        int hign=a.length-1;
        while (low<=hign){
            mid=(low+hign)>>>1;
            System.out.println("strat:"+low+",mid:"+mid+",end:"+hign+",a[mid]="+a[mid]+",valu="+valu);
            if(a[mid]==valu){
                System.out.println(valu);
                return mid;
            }else if(a[mid]<valu){
                low=mid+1;
            }else {
                hign=mid-1;
            }

        }
        System.out.println("strat:"+low+",mid:"+mid+",end:"+hign+",a[mid]="+a[mid]+",valu="+valu);
        return low;
    }

    private static String FF1(String ss){
        if(ss.equals("")){
            return "";
        }else{
            sub2=FF1(ss.substring(1));
            re1=sub2+ss.substring(0,1);
            return re1;
        }
    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        System.out.println(1>>2);

        bser(12);
        FF1("abcd");
        FF(6);
        System.out.println(re1);

        InetAddress host = InetAddress.getLocalHost();
        String ip = host.getHostAddress();
        String ipRange = ip.substring(0, ip.lastIndexOf('.'));
        System.out.println("本机ip地址：" + ip);
        System.out.println("网段是: " + ipRange);

        List<String> ips = Collections.synchronizedList(new ArrayList<>());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        AtomicInteger number = new AtomicInteger();
        for (int i = 0; i < 255; i++) {
            String testIP = ipRange + "." + (i + 1);
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    boolean reachable = isReachable(testIP);
                    if (reachable)
                        // System.out.println("找到可连接的ip地址：" + testIP);
                        ips.add(testIP);

                    synchronized (number) {
                        System.out.println("已经完成:" + number.incrementAndGet() + " 个 ip 测试");
                    }
                }

            });

        }

        // 等待所有线程结束的时候，就关闭线程池
        threadPool.shutdown();
        //等待线程池关闭，但是最多等待1个小时
        if (threadPool.awaitTermination(1, TimeUnit.HOURS)) {
            System.out.println("如下ip地址可以连接");
            for (String theip : ips) {
                System.out.println(theip);
            }
            System.out.println("总共有:" + ips.size() + " 个地址");

        }
    }
    private static boolean isReachable(String ip) {
        try {
            boolean reachable = false;

            Process p = Runtime.getRuntime().exec("ping -n 1 " + ip);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.length() != 0)
                    sb.append(line + "\r\n");
            }

            //当有TTL出现的时候，就表示连通了
            reachable = sb.toString().contains("TTL");
            br.close();
            return reachable;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

}
