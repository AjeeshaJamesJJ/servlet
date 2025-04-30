package Login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.ConnectDB;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/signinvalidate")
public class SigninValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("num");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String conpassword = request.getParameter("cpass");
		ConnectDB cdb;
		try {
			cdb = new ConnectDB();
			String exist = "SELECT * FROM users WHERE email='" + email + "';";
			ResultSet rs = cdb.read(exist);
			if (rs.next()) {
				out.println("User already exist. Please login!!!");
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.include(request, response);
			} else if (password.equals(conpassword)) {
				cdb.create("INSERT INTO users(firstname,lastname,phone,email,password) VALUES('" + fname + "','" + lname
						+ "','" + phone + "','" + email + "','" + password + "');");
				out.println("You have successfully signed in, Please login to access your dashboard!!!");
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.include(request, response);
			} else {
				out.println("Password mismatch, Sign in again!!!");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			cdb.close();
			out.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
