package business.facade;

import business.system.Projet;
import business.system.UserStory;

import java.util.ArrayList;
import java.util.List;

public class UserStoryFacade {

    // TODO

    public List<UserStory> getUserStoryByProject(Projet currentProject) {
        return new ArrayList<>();
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) {
        return true;
    }

    public boolean deleteUserStory(UserStory oldUS) {
        return true;
    }

    public boolean addUserStory(UserStory newUS) {
        return false;
    }
}