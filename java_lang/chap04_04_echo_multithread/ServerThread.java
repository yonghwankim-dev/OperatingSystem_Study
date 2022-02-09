package java_lang.chap04_04_echo_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

	private Socket client;

	public ServerThread(Socket client) {
		this.client = client;
	}



	@Override
	public void run() {
		BufferedReader br=null;
		PrintWriter pw = null;
		try {
			String connIp = client.getInetAddress().getHostAddress();
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    	pw = new PrintWriter(client.getOutputStream());
	    	
	    	// 클라이언트에서 받은 문자 저장
	    	String msg = br.readLine();
	    	
	    	// 클라이언트에서 받은 문자 출력
	    	System.out.println(connIp + " : " + msg);
	    	
	    	// 클라이언트에 문자열 전송
	    	pw.println(msg);
	    	pw.flush();
	    	client.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(br!=null)
				{
					br.close();
				}
				if(pw!=null)
				{
					pw.close();
				}
				if(client!=null)
				{
					client.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	

}
