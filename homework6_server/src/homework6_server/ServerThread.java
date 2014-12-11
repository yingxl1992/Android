package homework6_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
	
	Socket s=null;
	BufferedReader br=null;
	
	public ServerThread(Socket s) throws IOException {
		this.s=s;
		br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public void run() {
		try {
			String line=null;
			while((line=readFromClient())!=null) {
				System.out.println(line);
				for(Socket socket:ChatServer.clients) {					
						OutputStream os=socket.getOutputStream();
						os.write((line+"\n").getBytes());
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    private String readFromClient(){
    	try{
    		return br.readLine();
    	}
    	catch(IOException e){
    		ChatServer.clients.remove(s);
    	}
    	return null;
    }

}

