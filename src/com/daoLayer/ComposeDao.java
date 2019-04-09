package com.daoLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conProvider.ConProvider;
import com.mailer.Formatter;

public class ComposeDao {

	public static int save(String sender,String receiver,String subject,String message){
		int status=0;
		try{
			Connection con=ConProvider.getConnection();
			int Id = 0;
			PreparedStatement ps1 = con.prepareStatement("select MAX(id) from company_mailer_user");
			ResultSet rs = ps1.executeQuery();
			if(rs.next())
				Id = rs.getInt(1);
			Id = Id + 1;
			PreparedStatement ps=con.prepareStatement("insert into company_mailer_message(id,sender,receiver,subject,message,trash,messagedate) values(?,?,?,?,?,?,?)");
			ps.setInt(1, Id);
			ps.setString(2,sender);
			ps.setString(3,receiver);
			ps.setString(4,subject);
			ps.setString(5,message);
			ps.setString(6,"no");
			ps.setDate(7,Formatter.getCurrentDate());
			
			status=ps.executeUpdate();
						
		}catch(Exception e){System.out.println(e);}
				
		return status;
	}
}
