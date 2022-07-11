package chap03_09_echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) throws Exception {

		// ���� ���� ����
		Socket server = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = null;
		PrintWriter pw=null;
		
		System.out.println("������ ������...");
		try {
			while(true)
			{
				server = new Socket("127.0.0.1", 6013);
										
				// �޽��� �ۼ�
				System.out.println("������ ���� �޽����� �ۼ����ּ���. (���� : exit)");
				String msg = input.readLine();
				
				// ä�� ����
				if(msg.equals("exit"))
				{
					break;
				}
				
				// �޽��� ����
				pw = new PrintWriter(server.getOutputStream());
				pw.println(msg);
				pw.flush();

				// �޽��� �ޱ�
				br = new BufferedReader(new InputStreamReader(server.getInputStream()));

				// ���� ���
				System.out.println(br.readLine());
				
				br.close();
				pw.close();
				server.close();
			}

		}catch (ConnectException e) {
			System.out.println("������ �����ֽ��ϴ�.");
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