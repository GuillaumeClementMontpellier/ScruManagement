package DAO.mariadb;

import DAO.UserDAO;
import business.system.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOMariaDB extends DAOMariaDB implements UserDAO {

    public UserDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public User getUserByID(String mail, String password) throws SQLException {
        // Create query
        String sql = "Select idUser, firstNameUser, lastNameUser from User " +
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
        int id = resultSet.getInt("idUser");
        String firstName = resultSet.getString("firstNameUser");
        String lastName = resultSet.getString("lastNameUser");

        return new User(id, firstName, lastName, mail);
    }

    @Override
    public void registerUser(String mail, String password, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO User(emailUser,passWordUser,firstNameUser,lastNameUser) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, mail);
        pre.setString(2, password);
        pre.setString(3, firstName);
        pre.setString(4, lastName);

        pre.execute();
    }


    @Override
    public boolean userExists(String mail) throws SQLException {
        String sql = "Select idUser from User " +
                "Where emailUser = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, mail);

        ResultSet resultSet = pre.executeQuery();

        return resultSet.first();
    }

    @Override
    public User getUserByIdUser(int idUser) throws SQLException {
        // Prepare Query
        String sql = "Select * FROM User WHERE idUser = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idUser);

        // Execute Query
        ResultSet resultSet = pre.executeQuery();

        // Parse Query
        int id = resultSet.getInt("idUser");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        return new User(id, firstName, lastName, email);
    }

}
