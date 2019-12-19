package DAO;

import business.system.UserStory;

import java.sql.SQLException;

public abstract class UserStoryDAO extends DAO {

    public UserStoryDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public abstract UserStory getUserStoryById(int id);
    public abstract UserStory getUserStoryByProjectID(int id);
    public abstract boolean deleteUserStory(int id);
    public abstract boolean updateUserStory(int id, UserStory newUS);
    public abstract boolean addUserStory(UserStory newUS);
}