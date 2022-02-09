package java_lang.chap03_09_echo;

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
            	System.out.println(connIp + "에서 연결 시도");
            	
            	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            	pw = new PrintWriter(client.getOutputStream());
            	
            	// 클라이언트에서 받은 문자 저장
            	String msg = br.readLine();
            	
            	if(msg==null)
            	{
            		System.out.println(connIp+"에서 연결을 종료합니다.");
            		break;
            	}
            	
            	// 클라이언트에서 보낸 문자 출력
            	System.out.println(msg);
            	
            	// 클라이언트에 문자열 전송
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