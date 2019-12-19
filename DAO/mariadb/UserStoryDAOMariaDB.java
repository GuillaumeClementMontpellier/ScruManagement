package DAO.mariadb;

import DAO.*;
import business.system.UserStory;

import java.sql.SQLException;

public class UserStoryDAOMariaDB extends UserStoryDAO {
    public UserStoryDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    // TODO : implement auto-generated methods

    @Override
    public UserStory getUserStoryById(int id) {
        return null;
    }

    @Override
    public UserStory getUserStoryByProjectID(int id) {
        return null;
    }

    @Override
    public boolean deleteUserStory(int id) {
        return false;
    }

    @Override
    public boolean updateUserStory(int id, UserStory newUS) {
        return false;
    }

    @Override
    public boolean addUserStory(UserStory newUS) {
        return false;
    }
}