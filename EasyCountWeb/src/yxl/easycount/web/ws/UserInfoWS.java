package yxl.easycount.web.ws;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.jws.WebService;

import yxl.easycount.web.ws.dao.EasycountDao;

@WebService(endpointInterface="yxl.easycount.web.ws.IUserInfoWS",serviceName="UserInfo")
public class UserInfoWS implements IUserInfoWS{
	
//	@Override
//	public ArrayList<String> getUserInfo(String id) {
//		
//
//        try { 
//        	
//        	Connection conn = EasycountDao.getEasyCountDBConnection();
//        	
//        	ResultSet rs = EasycountDao.e;
//
//        	ArrayList<String> arrayList=new ArrayList<String>();
//        	
//   
//        	Statement statement = conn.createStatement();
//        	String sql = "select * from userinfo where id="+id;
//        	ResultSet rs = statement.executeQuery(sql);
//
//        	String name;
//        	while(rs.next()) {
//        		name = rs.getString("username");
//        		name = new String(name.getBytes("ISO-8859-1"),"GB2312");
//	        	
//	    		userArrayList.add(0,name);
//	    		userArrayList.add(0,rs.getString("password"));
//	         }
//
//        	rs.close();
//        	conn.close();
//
//        } catch(ClassNotFoundException e) {
//        	System.out.println("Sorry,can`t find the Driver!"); 
//        	e.printStackTrace();
//        } catch(SQLException e) {
//        	e.printStackTrace();
//        } catch(Exception e) {
//        	e.printStackTrace();
//        } 
//		
//		return userArrayList;
//	}

	@Override
	public String getPwdByName(String name) {
		Connection conn = EasycountDao.getEasyCountDBConnection();
    	String sql="select * from userinfo where username='"+name+"'";
    	System.out.println(sql);
    	ResultSet rs = EasycountDao.excuteQueryOper(sql, conn);
    	System.out.println(rs);
    	String password = null;
    	try {
    		if(rs.next()) {
        		password = rs.getString("password");
        		password = new String(password.getBytes("ISO-8859-1"),"GB2312");
        		
    		} else {
				password="没有结果";
			}
        	rs.close();
        	conn.close();        	
		} catch (Exception e) {
			e.printStackTrace();
		}   	
		return password;
	}

	@Override
	public int addUser(String name, String password) {
		Connection con=EasycountDao.getEasyCountDBConnection();
		String sqlString="insert into userinfo(username,password) values('"+name+"','"+password+"')";
		int res=EasycountDao.excuteOper(sqlString, con);
		return res;
	}
	
}
