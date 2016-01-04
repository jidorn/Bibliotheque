package fr.afcepf.al26.bibliotheque.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class Al26DataSource implements DataSource {

	private static String login;
	private static String mdp;
	private static String url;
	private static String driver;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Al26DataSource.class);
	
	static{
		log.info("Chargement des parametres du fichier de properties");
	ResourceBundle rb = ResourceBundle.getBundle("al26_jdbc_config");
	url = rb.getString("url");
	mdp = rb.getString("mdp");
	login = rb.getString("login");
	driver = rb.getString("driver");
	
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	log.info("datasource prete");
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		
		Connection cnx = DriverManager.getConnection(url, login, mdp);
		log.info("Connexion OK");
		return cnx;
	}
	
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
