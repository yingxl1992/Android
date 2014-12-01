package yxl.easycount.web.ws;

import javax.xml.ws.Endpoint;

public class WSApi {

	public static void main(String[] args) {
		
             System.out.println("web service start");
             UserInfoWS implementor= new UserInfoWS();
             String address="http://localhost:8080/easycountbg";
             Endpoint.publish(address, implementor);
             System.out.println("web service started");
	}

}
