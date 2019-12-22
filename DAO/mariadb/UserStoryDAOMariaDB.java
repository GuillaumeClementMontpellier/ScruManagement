package DAO.mariadb;

import DAO.UserStoryDAO;
import business.system.UserStory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserStoryDAOMariaDB extends UserStoryDAO {

    public UserStoryDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    // TODO : implement auto-generated methods

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

        String descr = resultSet.getString("descriptionUserStory");
        int projetID = resultSet.getInt("projetID");
        int score = resultSet.getInt("score");
        Date deadline = resultSet.getDate("deadline");

        return new UserStory(id, descr, projetID, score, deadline);
    }

    @Override
    public List<UserStory> getUserStoryByProjectID(int projetID) throws SQLException {
        String sql = "Select * from UserStory " +
                "Where projetID = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, projetID);

        ResultSet resultSet = pre.executeQuery();

        boolean success = resultSet.first();

        if (!success) {
            return null;
        }

        List<UserStory> list = new ArrayList<>();

        String description = resultSet.getString("descriptionUserStory");
        int USid = resultSet.getInt("idUserStory");
        int score = resultSet.getInt("score");
        Date deadline = resultSet.getDate("deadline");

        list.add(new UserStory(USid, description, projetID, score, deadline));

        while (resultSet.next()) {
            description = resultSet.getString("descriptionUserStory");
            USid = resultSet.getInt("projetID");
            score = resultSet.getInt("score");
            deadline = resultSet.getDate("deadline");
            list.add(new UserStory(USid, description, projetID, score, deadline));
        }

        return list;

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
                "and score = ? " +
                "and deadline = ? " +
                "and descriptionUserStory = ? " +
                "where idUserStory = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, newUS.getScore());
        pre.setDate(2, newUS.getDeadline());
        pre.setString(3, newUS.getDescription());
        pre.setInt(4, id);

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }

    @Override
    public boolean addUserStory(UserStory newUS) throws SQLException {
        String sql = "Insert into UserStory " +
                "values (?, ?, ?, ?)";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, newUS.getProjetID());
        pre.setInt(2, newUS.getScore());
        pre.setDate(3, newUS.getDeadline());
        pre.setString(4, newUS.getDescription());

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }
}