package company.socket;


import company.gaiLun;
import company.hero;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;

public class Client {

    public static void main(String[] args) throws Exception {
     /*   MessageDigest md = MessageDigest.getInstance("MD5");
        //bean的描述器
        BeanInfo bin=Introspector.getBeanInfo(gaiLun.class,Object.class);
        PropertyDescriptor[] pds=bin.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds){
            System.out.println(pd.getReadMethod().toString());
        }
        hero h1=new hero();
        h1.setDamage(1);
        h1.setHp(100);
        h1.setName("马超");
        hero h2=new hero();
        BeanUtils.copyProperties(h2,h1);
        System.out.println(h2);*/
        try {

            Socket s = new Socket("127.0.0.1", 8791);

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
        }
    }
}
