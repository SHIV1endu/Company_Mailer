package com.daoLayer;
import java.sql.*;

import com.conProvider.ConProvider;
public class LoginDao {
	
	public static int validate(String email,String password){
		int Id = 0;
		try{
			Connection con=ConProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from company_mailer_user where email=? and password=? and authorized=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ps.setString(3,"yes");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				Id=rs.getInt(1);
			}
			
			
		}catch(Exception e){System.out.println(e);}
		
		return Id;
	}
}
