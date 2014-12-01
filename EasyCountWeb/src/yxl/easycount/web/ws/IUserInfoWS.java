package yxl.easycount.web.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserInfoWS {

//	ArrayList<String> getUserInfo(@WebParam(name="id")String id);
	String getPwdByName(@WebParam(name="username")String name);
	int addUser(@WebParam(name="username")String name,@WebParam(name="password")String password);
}
