package java_lang.chap04_02_date;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatetimeServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(6013);
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		while(true)
		{
			Socket client = server.accept();
			
			pool.submit(new DatetimeThread(client));
		}
		
	}
}