package com.mailer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daoLayer.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Cookie ck = new Cookie("email",email);
		response.addCookie(ck);
		Cookie ck1 = new Cookie("password", password);
		response.addCookie(ck1);
		int Id = LoginDao.validate(email, password);
		if(Id != 0){
			//out.print("you are successfully logged in!");
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("email", email);
			response.sendRedirect("InboxServlet");
			
		}else{
			out.print("<p>Sorry, username or password error</p>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
