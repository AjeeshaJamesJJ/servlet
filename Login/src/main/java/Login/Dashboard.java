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
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
					break;
				}
			}
		}

		try {
			ConnectDB cdb = new ConnectDB();
			ResultSet rs = cdb.read("SELECT * FROM users WHERE email='" + email + "';");
			if (rs.next()) {
				out.print(
						"<fieldset><legend>Welcome to your dashboard</legend><table><tbody><tr><td><p><b>NAME:</b></p></td><td><p>"
								+ rs.getString("firstname") + " " + rs.getString("lastname")
								+ "</p></td></tr><tr><td><p><b>EMAIL:</b></p></td><td><p>" + rs.getString("email")
								+ "</p></td></tr><tr><td><p><b>PHONE:</b></p></td><td><p>" + rs.getString("phone")
								+ "</p></td></tr></tbody></table></form></fieldset>");
			}
			cdb.close();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
