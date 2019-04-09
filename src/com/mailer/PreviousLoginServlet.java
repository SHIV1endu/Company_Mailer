package com.mailer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.daoLayer.LoginDao;


public class PreviousLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Cookie[] ck = request.getCookies();
		if(ck!=null){
			String email = ck[0].getValue();
			String password = ck[1].getValue();
			int Id = LoginDao.validate(email, password);
			if(Id != 0){
				//out.print("you are successfully logged in!");
				request.getSession().setAttribute("login", "true");
				request.getSession().setAttribute("email", email);
				response.sendRedirect("InboxServlet");
			}
		}
		else{
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}
}
