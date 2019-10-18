package com.company.socket;

import com.company.gaiLun;
import com.company.hero;
import org.omg.PortableInterceptor.InterceptorOperations;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IntrospectionException {

        //bean的描述器
        BeanInfo bin=Introspector.getBeanInfo(gaiLun.class,Object.class);
        PropertyDescriptor[] pds=bin.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds){
            System.out.println(pd.getReadMethod().toString());
        }
        /*try {

            Socket s = new Socket("127.0.0.1", 8888);

            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            dos.writeUTF("连接");
            System.out.println("收到服务端信息:"+dis.readUTF());
            new Send(s).start();
            new Recieve(s).start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
