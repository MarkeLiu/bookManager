package com.yfl.service;

import java.util.Vector;

import com.yfl.modle.User;
import com.yfl.utils.SqlHelper;

public class UserService {

	//验证用户是否合法
	public boolean checkUser(User user){
		
		boolean b=false;
		String sql="select * from users where userName=? and passwd=?";
		String parameters[]={user.getUserName(),user.getPasswd()};
		Vector<Object[]> vector=new SqlHelper().executeQuery(sql, parameters);
		if(vector.size()!=0){
			b=true;
		}
		return b;
	}
}
