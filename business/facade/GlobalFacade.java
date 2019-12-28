package business.facade;

import business.system.Projet;
import business.system.User;
import business.system.UserStory;

import java.sql.SQLException;
import java.util.List;

public class GlobalFacade {

    // Singleton
    private static GlobalFacade instance = null;

    private GlobalFacade() {

        this.loginFacade = new LoginFacade();
        this.userStoryFacade = new UserStoryFacade();
    }

    public static GlobalFacade getInstance() {
        if (instance == null) {
            instance = new GlobalFacade();
        }
        return instance;
    }

    // Logic
    private final UserStoryFacade userStoryFacade;

    private LoginFacade loginFacade;

    public User login(String username, String password) throws SQLException {
        return loginFacade.login(username, password);
    }

    // TODO : redo type and params
    public boolean addUserStory(UserStory newUS) throws SQLException {
        return userStoryFacade.addUserStory(newUS);
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) throws SQLException {
        return userStoryFacade.updateUserStory(oldUS, newUS);
    }

    public List<UserStory> getUserStoryByProject(Projet currentProject) throws SQLException {
        return userStoryFacade.getUserStoryByProject(currentProject);
    }

    public boolean deleteUserStory(UserStory oldUS) throws SQLException {
        return userStoryFacade.deleteUserStory(oldUS);
    }

    public UserStory getUserStoryByID(int id) throws SQLException {
        return userStoryFacade.getUserStoryByID(id);
    }

}
