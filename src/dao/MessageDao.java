package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sistema_correio.Message;
import sistema_correio.User;
import daoFactory.DaoFactory;

public class MessageDao {
	
	private static final String 
	INSERT = "INSERT INTO messages (user_from_id, body, created_at) VALUES (?, ?, ?)";
	
	private static final String 
	FIND_BY_ID = "SELECT * FROM messages WHERE id = ?";
	
	/**
	 * Method insert a message in the database
	 * @param message
	 * @return message The message inserted
	 * @throws SQLException 
	 */
	public Message insert(Message message) throws SQLException {
		Connection c = DaoFactory.getDaoFactory().openConnection();

		PreparedStatement pstmt = c.prepareStatement(INSERT);
		
		pstmt.setLong(1, message.getFrom().getId());
		pstmt.setString(2, message.getBody());
		pstmt.setDate(3, message.getCreatedAt());

		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();
		rset.next();

		Long idGenerated = rset.getLong(1);
		message.setId(idGenerated);
		
		realtionMessageUser(message);
		
		pstmt.close();
		c.close();

		return message;
	}
	
	/**
	 * Method to find a Message by id
	 * @return message Message find by the id, otherwise null	
	 * @throws SQLException
	 */
	public Message findById(Long id) throws SQLException {
		Connection c = DaoFactory.getDaoFactory().openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setLong(1, id);
		
		Message message = null;
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			message = createMessage(rset);
		}

		pstmt.close();
		c.close();

		return message;
	}
	
	private Message createMessage(ResultSet rset) throws SQLException{
		User from = User.findById(rset.getLong("user_from_id"));
		
		String body = rset.getString("body");
		
		List<User> users = Message.receiversOf(rset.getLong("id"));
		String[] logins = new String[users.size()];
		
		for (int i = 0; i < logins.length; i++) {
			logins[i] = users.get(i).getLogin();
		}	
		
		Message message = new Message(from, body, logins);
		message.setId(rset.getLong("id"));
		
		return message;
	}
	
	private void realtionMessageUser(Message message) throws SQLException{
		DaoFactory dao = DaoFactory.getDaoFactory();
		UsersMessagesDao usersMessages = dao.getUsersMessagesDao();
		usersMessages.insert(message);
	}
	
}
