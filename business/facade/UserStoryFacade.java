package business.facade;

import DAO.UserStoryDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Column;
import business.system.ProductBacklog;
import business.system.Project;
import business.system.UserStory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserStoryFacade {

    private BacklogFacade backlogFacade;

    public void setBacklogFacade(BacklogFacade backlogFacade){
        this.backlogFacade = backlogFacade;
    }

    public UserStory[] getUserStoryByProject(Project project) throws SQLException {
        List<UserStory> lus = new ArrayList<>();
        ProductBacklog pb = backlogFacade.getProductBacklog(project);
        Column[] column = backlogFacade.getColumn(pb);
        for (Column c : column) {
            UserStory[] userStory = backlogFacade.getUserStory(c);
            lus.addAll(Arrays.asList(userStory));
        }
        return lus.toArray(new UserStory[lus.size()]);
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

    public boolean addUserStory(UserStory newUS, Project project) throws SQLException {

        UserStoryDAO usDAO = AbstractFactoryDAO.getInstance().createUserStoryDAO();
        boolean success = usDAO.addUserStory(newUS);

        // Add to column
        ProductBacklog pb = backlogFacade.getProductBacklog(project);
        Column column = backlogFacade.getColumn(pb)[1];

        backlogFacade.addComponent(newUS, column);

        return success;
    }
}
