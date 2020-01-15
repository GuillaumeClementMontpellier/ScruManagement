package DAO.mariadb;

import DAO.TicketDAO;
import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOMariaDBTest {

    // prerequesite: userExists working
    @Test
    public void registerUser() {
        try {
            UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
            userDAO.registerUser("test@test", "testest", "Test", "TEST", "test");

            if (userDAO.userExists("test@test")) {
                System.out.println("registerUser: Success");
            }
            else {
                System.out.println("registerUser: Failed, either there is no user created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("registerUser: Failed, Database ERROR at the execution");
        }
    }
}