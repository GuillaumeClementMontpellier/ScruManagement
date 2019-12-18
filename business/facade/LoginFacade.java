package business.facade;

import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.User;

import java.sql.SQLException;

public class LoginFacade {

    public User login(String mail, String password) throws SQLException {
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        //TODO encrypt password
        return userDAO.getUserByID(mail, password);
    }

    public User register(String mail, String password, String firstName, String lastName) throws SQLException {
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        if (userDAO.userExists(mail)) {
            return null;
        }
        //TODO encrypt password
        if (userDAO.registerUser(mail, password, firstName, lastName)) {
            return login(mail, password);
        } else {
            return null;
        }
    }
}
