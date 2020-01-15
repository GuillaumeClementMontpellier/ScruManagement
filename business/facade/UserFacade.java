package business.facade;

import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.User;
import util.Cryptor;

import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class UserFacade {

    public User login(String mail, String password) throws SQLException {
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();

        // getSalt
        String salt = userDAO.getSalt(mail);
        if (salt == null) {
            return null;
        }

        // Generate Hash
        String encryptedPassword = null;
        try {
            encryptedPassword = Cryptor.generateHash(password, salt);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }

        // Get User by id, password
        return userDAO.getUserByID(mail, encryptedPassword);
    }

    public User register(String mail, String password, String firstName, String lastName) throws SQLException {
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        if (userDAO.userExists(mail)) {
            return null;
        }
        // Generate Hash
        String salt = Cryptor.getSaltRandom();
        String encryptedPassword = null;
        try {
            encryptedPassword = Cryptor.generateHash(password, salt);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }

        userDAO.registerUser(mail, encryptedPassword, firstName, lastName, salt);
        return login(mail, password);
    }
}
