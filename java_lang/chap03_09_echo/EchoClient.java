package java_lang.chap03_09_echo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) throws Exception {

		// 서버 소켓 생성
		Socket server = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = null;
		PrintWriter pw=null;
		
		System.out.println("서버에 접속중...");
		try {
			while(true)
			{
				server = new Socket("127.0.0.1", 6013);
										
				// 메시지 작성
				System.out.println("서버에 보낼 메시지를 작성해주세요. (종료 : exit)");
				String msg = input.readLine();
				
				// 채팅 종료
				if(msg.equals("exit"))
				{
					break;
				}
				
				// 메시지 전달
				pw = new PrintWriter(server.getOutputStream());
				pw.println(msg);
				pw.flush();

				// 메시지 받기
				br = new BufferedReader(new InputStreamReader(server.getInputStream()));

				// 응답 출력
				System.out.println(br.readLine());
				
				br.close();
				pw.close();
				server.close();
			}

		}catch (ConnectException e) {
			System.out.println("서버가 닫혀있습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)
			{
				br.close();
			}
			if(pw!=null)
			{
				pw.close();
			}
			if(input!=null)
			{
				input.close();
			}
			if(server!=null)
			{
				server.close();
			}
		}
	}
}