package DAO.mariadb;

import DAO.UserDAO;
import business.system.User;

import java.sql.*;

public class UserDAOMariaDB extends UserDAO {

    private Connection connection;
    private Statement statement;

    public UserDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {

        this.connection = DriverManager.getConnection(addressDataBase, userDataBase, passWordDataBase);

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

		if (!success){
			return null;
		}

		//parse result set
		String firstName = resultSet.getString("firstNameUser");
		String lastName = resultSet.getString("lastNameUser");

        return new User(firstName, lastName, mail);
    }

//    public static void main(String[] args) throws SQLException {
//
//		// statement.executeQuery : la requète sql (string) est crée par le DAO : elle
//		// dépends du DAO
//
//		ResultSet res = stmt.executeQuery("SELECT * FROM information_schema.TABLES;");
//
//		// A partir de là, c'est dans le DAO (UserDAOMariaDB ou ..., en fonction de la
//		// table, pour extraire les données du ResultSet et en faire un User, Project ou
//		// autre chose)
//
//		ResultSetMetaData md = res.getMetaData();
//
//
//		int columnCount = md.getColumnCount();
//
//		res.first();
//
//		int rowCount = 0;
//
//		while (res.next()) {
//
//			String str = "" + rowCount;
//
//			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//
//				String value = res.getString(columnIndex);
//
//				str += "\t" + value;
//			}
//
//			System.out.println(str);
//
//			rowCount++;
//		}
//
//		// Close lors de la destruction du DAO
//
//		stmt.close();
//
//		connection.close();
//	}

}
