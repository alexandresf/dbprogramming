package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import model.Message;
//import model.User;
import daoFactory.DaoFactory;
import sistema_correio.Message;
import sistema_correio.User;

public class UsersMessagesDao {
	
	private static final String 
	INSERT = "INSERT INTO users_messages (user_id, message_id) VALUES (?, ?)";
	
	private static final String 
	FIND_MESSAGES_FROM_USER = "SELECT message_id FROM users_messages WHERE user_id = ?";
	
	private static final String 
	FIND_USERS_FROM_MESSAGE_ID = "SELECT user_id FROM users_messages WHERE message_id = ?";
	
	/**
	 * Method to create the relation user message
	 * @param message
	 * @throws SQLException 
	 */
	public void insert(Message message) throws SQLException {
		Connection c = DaoFactory.getDaoFactory().openConnection();

		PreparedStatement pstmt = c.prepareStatement(INSERT);
		
		for (String login : message.getTo()) {
			
			pstmt.setLong(2, message.getId());
			User to = User.findByLogin(login);
			
			if (to == null)
				createAbsentUserMessage(message);
			else{
				pstmt.setLong(1, to.getId());
				pstmt.executeUpdate();
			}
		}
		
		pstmt.close();
		c.close();
	}
	

	/**
	 * Method to retrieve all messages from a user
	 * @param id the id of the user
	 * @return all messages from a user	
	 * @throws SQLException 
	 */
	public List<Message> messagesFromUserId(Long id) throws SQLException {
		Connection c = DaoFactory.getDaoFactory().openConnection();

		PreparedStatement pstmt;
		pstmt = c.prepareStatement(FIND_MESSAGES_FROM_USER);
		pstmt.setLong(1, id);
		
		ArrayList<Message> messages = new ArrayList<Message>();
		ResultSet rset = pstmt.executeQuery();
		
		while (rset.next()){
			messages.add(Message.findById(rset.getLong("message_id")));
		}

		pstmt.close();
		c.close();	
	
		return messages;
	}

	/**
	 * Method to retrieve all user from a message
	 * @param id The id of the message
	 * @return users all the users that received that message	
	 * @throws SQLException 
	 */
	public List<User> usersFromMessageId(Long id) throws SQLException {
		Connection c = DaoFactory.getDaoFactory().openConnection();

		PreparedStatement pstmt;
		pstmt = c.prepareStatement(FIND_USERS_FROM_MESSAGE_ID);
		pstmt.setLong(1, id);

		ArrayList<User> users = new ArrayList<User>();
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			users.add(User.findById(rset.getLong("user_id")));
		}

		pstmt.close();
		c.close();

		return users;
	}
	
	/* method to create and send a message notify that a user doesn't exists*/
	private void createAbsentUserMessage(Message message) throws SQLException{
		String msg = "\n Contato Inexistente!!\n" + message.getBody();
		
		Message unDeliveredMessage = new Message(message.getFrom(),
												 msg,
												 message.getFrom().getLogin());
		unDeliveredMessage.save();
	}
}
