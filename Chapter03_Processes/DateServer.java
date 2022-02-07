import java.net.*;
import java.io.*;

public class DateServer{
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(6013);

        // now listen for connections
        while(true)
        {
            Socket client = server.accept();
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

            // socket에 Date 정보 작성
            pout.println(new java.util.Date().toString());

            // client socket 해제
            client.close();
        }
    }
}