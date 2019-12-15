package business.facade;

import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.User;

import java.sql.SQLException;

public class LoginFacade implements Facade{

    public User login(String mail, String password) throws SQLException {
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();

        return userDAO.getUserByID(mail, password);
    }
}
