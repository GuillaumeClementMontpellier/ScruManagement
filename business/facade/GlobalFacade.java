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

    public User register(String username, String password, String firstName, String lastName) throws SQLException {
        return loginFacade.register(username,password,firstName,lastName);
    }

    public boolean addUserStory(UserStory newUS, int projectID) throws SQLException {
        return userStoryFacade.addUserStory(newUS, projectID);
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) throws SQLException {
        return userStoryFacade.updateUserStory(oldUS, newUS);
    }

    public boolean deleteUserStory(UserStory oldUS) throws SQLException {
        return userStoryFacade.deleteUserStory(oldUS);
    }

    public UserStory getUserStoryByID(int id) throws SQLException {
        return userStoryFacade.getUserStoryByID(id);
    }

}
