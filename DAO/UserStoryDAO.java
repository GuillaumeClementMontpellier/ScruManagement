package DAO;

import business.system.UserStory;

import java.sql.SQLException;

public abstract class UserStoryDAO extends DAO {

    public UserStoryDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public abstract UserStory getUserStoryById(int id) throws SQLException;

    public abstract boolean deleteUserStory(int id) throws SQLException;

    public abstract boolean updateUserStory(int id, UserStory newUS) throws SQLException;

    public abstract boolean addUserStory(UserStory newUS, int projectID) throws SQLException;
}