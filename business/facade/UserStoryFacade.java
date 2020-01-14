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

    private GlobalFacade globalFacade;

    public void setGlobalFacade(GlobalFacade globalFacade){
        this.globalFacade = globalFacade;
    }

    public UserStory[] getUserStoryByProject(Project project) throws SQLException {
        List<UserStory> lus = new ArrayList<>();
        ProductBacklog pb = globalFacade.getProductBacklog(project);
        Column[] column = globalFacade.getColumn(pb);
        for (Column c : column) {
            UserStory[] userStory = globalFacade.getUserStory(c);
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

        if (!success) {
            return false;
        }

        // Add to column
        ProductBacklog pb = globalFacade.getProductBacklog(project);
        Column column = globalFacade.getColumn(pb)[0];

        success = globalFacade.addComponent(newUS, column);

//        System.out.println("success 2 = " + success);
        return success;
    }
}
