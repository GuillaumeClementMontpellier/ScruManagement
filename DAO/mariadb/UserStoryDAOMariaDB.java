package DAO.mariadb;

import DAO.UserStoryDAO;
import business.system.UserStory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStoryDAOMariaDB extends UserStoryDAO {

    public UserStoryDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public UserStory getUserStoryById(int id) throws SQLException {
        String sql = "Select * from UserStory " +
                "Where idUserStory = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, id);

        ResultSet resultSet = pre.executeQuery();

        boolean success = resultSet.first();

        if (!success) {
            return null;
        }

        String desc = resultSet.getString("descriptionUserStory");
        int score = resultSet.getInt("score");
        Date deadline = resultSet.getDate("deadline");
        String name = resultSet.getString("nameUserStory");

        return new UserStory(id, name, desc, score, deadline);
    }

    @Override
    public boolean deleteUserStory(int id) throws SQLException {
        String sql = "DELETE from UserStory where idUserStory = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, id);

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }

    @Override
    public boolean updateUserStory(int id, UserStory newUS) throws SQLException {
        String sql = "UPDATE UserStory " +
                "SET score = ? " +
                "and deadline = ? " +
                "and descriptionUserStory = ? " +
                "and name = ? " +
                "where idUserStory = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, newUS.getScore());
        pre.setDate(2, newUS.getDeadline());
        pre.setString(3, newUS.getDescription());
        pre.setString(4, newUS.getName());
        pre.setInt(5, id);

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }

    @Override
    public boolean addUserStory(UserStory newUS) throws SQLException {
        String sql = "Insert into UserStory " +
                "values ( ?, ?, ?, ?)";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, newUS.getScore());
        pre.setDate(2, newUS.getDeadline());
        pre.setString(3, newUS.getDescription());
        pre.setString(4, newUS.getName());

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }
}