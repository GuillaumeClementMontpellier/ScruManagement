package DAO.mariadb;

import DAO.UserStoryDAO;
import business.system.UserStory;

import java.sql.*;

public class UserStoryDAOMariaDB extends DAOMariaDB implements UserStoryDAO {

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

        // Delete ColumnUserStory
        String sql = "DELETE from ColumnUserStory where idComponent = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, id);

        int nbAff = pre.executeUpdate();

        // Delete User Story
        sql = "DELETE from UserStory where idUserStory = ?";

        pre = this.connection.prepareStatement(sql);

        pre.setInt(1, id);

        int nbAffected = pre.executeUpdate();

        return (nbAffected > 0) && (nbAff > 0);
    }

    @Override
    public boolean updateUserStory(int id, UserStory newUS) throws SQLException {
        String sql = "UPDATE UserStory " +
                "SET score = ?, " +
                "deadline = ?, " +
                "descriptionUserStory = ?, " +
                "nameUserStory = ? " +
                "where idUserStory = ?";

        System.out.println(id);
        System.out.println(newUS.getName());

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, newUS.getScore());
        pre.setDate(2, newUS.getDeadline());
        pre.setString(3, newUS.getDescription());
        pre.setString(4, newUS.getName());
        pre.setInt(5, id);

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }

    /**
     * If success, set UserStory id to generated id ?
     *
     * @param newUS
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addUserStory(UserStory newUS) throws SQLException {
        String sql = "Insert into UserStory(score, deadline, descriptionUserStory, nameUserStory) " +
                "values ( ?, ?, ?, ?)";

        PreparedStatement pre = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pre.setInt(1, newUS.getScore());
        pre.setDate(2, newUS.getDeadline());
        pre.setString(3, newUS.getDescription());
        pre.setString(4, newUS.getName());

        int nbAffected = pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();

        if (nbAffected < 0) {
            return false;
        }

        boolean first = rs.first();
        if (!first) {
            return false;
        }
        int idUS = rs.getInt(1);

        newUS.setId(idUS);
        return true;
    }
}