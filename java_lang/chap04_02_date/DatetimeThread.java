package java_lang.chap04_02_date;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DatetimeThread implements Runnable{
	private Socket client;
	
	public DatetimeThread(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		
		try {
			PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
			pout.println(new java.util.Date().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



	}

}
