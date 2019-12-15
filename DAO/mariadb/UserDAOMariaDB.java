package DAO.mariadb;

import DAO.DAO;
import DAO.UserDAO;
import business.system.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOMariaDB extends DAO implements UserDAO {

    public UserDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public User getUserByID(String mail, String password) throws SQLException {
        // Create query
        String sql = "Select firstNameUser, lastNameUser from User " +
                "Where emailUser=? and passWordUser=?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setString(1, mail);
        pre.setString(2, password);

        // Execute query
        ResultSet resultSet = pre.executeQuery();

        boolean success = resultSet.first();

        if (!success) {
            return null;
        }

        //parse result set
        String firstName = resultSet.getString("firstNameUser");
        String lastName = resultSet.getString("lastNameUser");

        return new User(firstName, lastName, mail);
    }

}
