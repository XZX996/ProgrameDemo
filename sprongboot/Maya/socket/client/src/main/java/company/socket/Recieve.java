package company.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Recieve extends Thread {
    private Socket s;
    public Recieve(Socket s){
        this.s=s;
    }

    @Override
    public void run() {
        try {

            InputStream is = s.getInputStream();

            DataInputStream dis = new DataInputStream(is);
            while (true) {
                String msg = dis.readUTF();
                System.out.println("接受到服务器消息:"+msg+'\n');
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
