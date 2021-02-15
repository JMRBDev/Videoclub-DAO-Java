package com.jmrbdev.videoclub.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static String bd = "videoclub_jose";
	static String port = "3306";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:" + port + "/" + bd;

	Connection connection = null;

	public DBConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);

			if (connection == null) {
				System.out.println("LA CONEXIÓN CON '" + bd + "' HA FALLADO\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void desconectar() {
		connection = null;
	}
}