package business.facade;

import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.User;

import java.sql.SQLException;

public class LoginFacade implements Facade{

    private AbstractFactoryDAO factory;

    public void setFactory(AbstractFactoryDAO factory){
        this.factory = factory;
    }

    public User login(String mail, String password) throws SQLException {
        UserDAO userDAO = factory.createUserDAO();

        return userDAO.getUserByID(mail, password);
    }
}
