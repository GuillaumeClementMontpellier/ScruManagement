package business.facade;

import DAO.UserStoryDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.UserStory;

import java.sql.SQLException;

public class UserStoryFacade {

    public UserStory getUserStoryByID(int id) throws SQLException {
        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();

        return usDAO.getUserStoryById(id);
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) throws SQLException {

        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();

        return usDAO.updateUserStory(oldUS.getId(), newUS);
    }

    public boolean deleteUserStory(UserStory oldUS) throws SQLException {

        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();

        return usDAO.deleteUserStory(oldUS.getId());
    }

    public boolean addUserStory(UserStory newUS) throws SQLException {

        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();

        return usDAO.addUserStory(newUS);
    }
}
