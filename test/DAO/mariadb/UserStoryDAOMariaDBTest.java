package DAO.mariadb;

import DAO.UserStoryDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.UserStory;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserStoryDAOMariaDBTest {

    // prerequesite: getUserStoryById working
    @Test
    public void addUserStory() {
        Date date = new Date(2020,01,14);
        UserStory newUS = new UserStory(8080, "UserStoryTest", "TestTestTest", 888, date);
        try {
            UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();
            boolean success = usDAO.addUserStory(newUS);

            UserStory dbUS = usDAO.getUserStoryById(8080);
            if (newUS.getId() == dbUS.getId()) {
                System.out.println("addUserStory: Success");
            }
            else if (!success) {
                System.out.println("addUserStory: Failed, function tells us something went wrong during the procedure");
            }
            else {
                System.out.println("addUserStory: Failed, either there is no user story created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("addUserStory: Failed, Database ERROR at the execution");
        }
    }
}