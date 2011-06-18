package daoFactory;

import java.sql.Connection;

import dao.MessageDao;
import dao.UserDao;
import dao.UsersMessagesDao;

//Abstract class DAO Factory
public abstract class DaoFactory {

  /* 
   * There will be a method for each DAO that can be
   * created. The concrete factories will have to
   * implement these methods.
   */
  public abstract Connection openConnection();	
  public abstract UserDao getUserDao();
  public abstract MessageDao getMessageDao();
  public abstract UsersMessagesDao getUsersMessagesDao();
  
  public static DaoFactory getDaoFactory() {
      return new MysqlDao();
  }
}
