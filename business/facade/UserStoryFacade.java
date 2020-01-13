package business.facade;

import DAO.UserStoryDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Column;
import business.system.ProductBacklog;
import business.system.Projet;
import business.system.UserStory;

import java.sql.SQLException;

public class UserStoryFacade {

    private BacklogFacade backlogFacade;

    public void setBacklogFacade(BacklogFacade backlogFacade){
        this.backlogFacade = backlogFacade;
    }

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

    public boolean addUserStory(UserStory newUS, Projet project) throws SQLException {

        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();
        boolean success = usDAO.addUserStory(newUS);

        // Add to column
        ProductBacklog pb = backlogFacade.getProductBacklog(project);
        Column column = backlogFacade.getColumn(pb)[1];

        backlogFacade.addComponent(newUS, column);

        return success;
    }
}
