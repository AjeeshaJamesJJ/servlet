package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {

	Statement stmt;
	Connection con;

	public ConnectDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
		stmt = con.createStatement();
	}

	public void create(String st) throws SQLException {
		stmt.executeUpdate(st);
	}

	public ResultSet read(String st) throws SQLException {
		return stmt.executeQuery(st);
	}

	public void close() throws SQLException {
		con.close();
	}
}
