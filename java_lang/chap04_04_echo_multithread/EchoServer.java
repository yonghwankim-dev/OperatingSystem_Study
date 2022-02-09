package java_lang.chap04_04_echo_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{
    public static void main(String[] args) throws Exception{
    	final int SERVER_PORT = 6013;
    	
        ServerSocket server = new ServerSocket(SERVER_PORT);
        Socket client = null;
        
        System.out.println(SERVER_PORT + " port server start");
        try {
        	while(true)
            {
            	client = server.accept();
            	new Thread(new ServerThread(client)).start();
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}finally {
			if(client!=null)
			{
				client.close();
			}
			if(server!=null)
			{
				server.close();
			}
		}
        
    }
}