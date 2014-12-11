package homework6_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	static ArrayList<Socket> clients=new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException{			
			ServerSocket serverSocket=new ServerSocket(30000);					
			while(true) {
				Socket socket=serverSocket.accept();
				clients.add(socket);				
				System.out.println(socket);
				new Thread(new ServerThread(socket)).start();					
			}
	}

}