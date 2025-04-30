package Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.ConnectDB;

/**
 * Servlet implementation class LoginValidate
 */
@WebServlet("/loginvalidate")
public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ConnectDB cdb;
		try {
			cdb = new ConnectDB();
			ResultSet rs = cdb.read("SELECT * FROM users WHERE email='"+email+"' and password='"+password+"';");
			if(rs.next()) {
				response.addCookie(new Cookie("email",email));
				response.addCookie(new Cookie("password",password));
				response.sendRedirect("dashboard");
			} else {
				out.println("Invalid credentials!!!");
				response.sendRedirect("login");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
