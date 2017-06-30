package com.okw.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static class singleInstance {
		private static DBUtil util = new DBUtil();
	}

	public static DBUtil getInstance() {
		return singleInstance.util;
	}

	Connection conn = DBConnection.getConnection();

	private DBUtil() {

	}

	public void DBInsert(Userinfo info) {
		String sql = "insert into userinfo(username,password)"+
	"values(\""+info.getUsername()+"\",\""+info.getPassword()+"\")";
		
		try {
			Statement state=conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}

	public void DBDelete(int id) {
		String sql = "delete from user where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DBUpdate(Userinfo info,int id) {
		String sql="update from user set username=?,password=? where id=?";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, info.getUsername());
			pst.setString(2, info.getPassword());
			pst.setInt(3, id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Userinfo DBQuery(String userName) {
		Userinfo info = null;
		try {
			Statement stat = null;
            if(conn!=null){
            	
            	 stat= conn.createStatement();
               ResultSet set = stat.executeQuery("select username,password from userinfo where username='"+userName+"'");
               info = new Userinfo();
               if(set.next()){
            	   do{
       				info.setUsername(set.getString("username"));
       				info.setPassword(set.getString("password"));
            	   }while(set.next());

            	   
               }

            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return info;

	}
}
