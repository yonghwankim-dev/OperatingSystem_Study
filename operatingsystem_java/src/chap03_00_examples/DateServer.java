package chap03_00_examples;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer{
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(6013);

        // now listen for connections
        while(true)
        {
            Socket client = server.accept();
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

            pout.println(new java.util.Date().toString());

            client.close();
        }
    }
}