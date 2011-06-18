package daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.MessageDao;
import dao.UserDao;
import dao.UsersMessagesDao;

public class MysqlDao extends DaoFactory{
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/";
	private static String database = "mailsystem";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "";
	
	public Connection openConnection() {   
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + database, user, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println(
				"Não foi possível salvar os dados! O Banco de dados não está respondendo!");
		}
		return null;
	}
	
	@Override
	public UserDao getUserDao() {
		return new UserDao();
	}

	@Override
	public MessageDao getMessageDao() {
		return new MessageDao();
	}

	@Override
	public UsersMessagesDao getUsersMessagesDao() {
		return new UsersMessagesDao();
	}
}
