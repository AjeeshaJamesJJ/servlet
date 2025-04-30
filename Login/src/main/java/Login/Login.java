package Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<fieldset><legend>Login Form</legend>");
		out.print("<form action='loginvalidate' method='post'>");
		out.print("<table><tbody><tr><td><label for='email'>Email:</label></td>");
		out.print("<td><input type='email' id='email' name='email' required/></td></tr>");
		out.print("<tr><td><label for='pass'>Password:</label></td>");
		out.print("<td><input type='password' id='pass' name='pass' required/></td></tr>");
		out.print("<tr><td/><td><input type='submit' value='SUBMIT'/></td></tr></tbody></table></form></fieldset>");
		out.close();
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
