/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema_correio;
import dao.UserDao;
import daoFactory.DaoFactory;
import java.sql.SQLException;

/**
 *
 * @author Gilson e Alexandre
 */
public class User {

    private String login,name;
    private Long id = null;

    public User(String login, String name){
        this.login = login;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome do usuário.
     * @return O nome do usuário.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o login do usuário.
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /*  Methods to work with the database **/
    /**
     * Method to save the current user in the database
     */
    public void save() throws SQLException {
        User saved = dao().insert(this);
        this.setId(saved.getId());
    }

    /*
     * Method to return the user DAO
     * @return dao the user dao
     */
    private static UserDao dao() {
        DaoFactory dao = DaoFactory.getDaoFactory();
        return dao.getUserDao();
    }
}
