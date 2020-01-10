package DAO;

import business.system.UserStory;

import java.sql.SQLException;

public interface UserStoryDAO {

    UserStory getUserStoryById(int id) throws SQLException;

    boolean deleteUserStory(int id) throws SQLException;

    boolean updateUserStory(int id, UserStory newUS) throws SQLException;

    boolean addUserStory(UserStory newUS) throws SQLException;
}