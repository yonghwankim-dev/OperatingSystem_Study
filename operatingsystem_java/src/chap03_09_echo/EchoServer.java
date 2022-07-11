package chap03_09_echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(6013);
        BufferedReader br = null;
        PrintWriter pw = null;
        Socket client = null;
        System.out.println("6013 port server start");
        
        try {
        	while(true)
            {
            	client = server.accept();
            	String connIp = client.getInetAddress().getHostAddress();
            	System.out.println(connIp + "���� ���� �õ�");
            	
            	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            	pw = new PrintWriter(client.getOutputStream());
            	
            	// Ŭ���̾�Ʈ���� ���� ���� ����
            	String msg = br.readLine();
            	
            	if(msg==null)
            	{
            		System.out.println(connIp+"���� ������ �����մϴ�.");
            		break;
            	}
            	
            	// Ŭ���̾�Ʈ���� ���� ���� ���
            	System.out.println(msg);
            	
            	// Ŭ���̾�Ʈ�� ���ڿ� ����
            	pw.println(msg);
            	pw.flush();
            	client.close();
            	
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}finally {
			if(pw!=null)
			{
				pw.close();
			}
			if(br!=null)
			{
				br.close();
			}
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