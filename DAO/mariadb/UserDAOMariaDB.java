package DAO.mariadb;

import DAO.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOMariaDB extends UserDAO {


    public UserDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public static void main(String[] args) throws SQLException {

		// Create connection : Doit mettre les infos du .conf

		Connection connection = DriverManager.getConnection(address, username, mpd);

		// create statement

		Statement stmt = connection.createStatement();

		// statement.executeQuery : la requète sql (string) est crée par le DAO : elle
		// dépends du DAO

		ResultSet res = stmt.executeQuery("SELECT * FROM information_schema.TABLES;");

		// A partir de là, c'est dans le DAO (UserDAOMariaDB ou ..., en fonction de la
		// table, pour extraire les données du ResultSet et en faire un User, Project ou
		// autre chose)

		ResultSetMetaData md = res.getMetaData();


		int columnCount = md.getColumnCount();

		res.first();

		int rowCount = 0;

		while (res.next()) {

			String str = "" + rowCount;

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {

				String value = res.getString(columnIndex);

				str += "\t" + value;
			}

			System.out.println(str);

			rowCount++;
		}

		// Close lors de la destruction du DAO

		stmt.close();

		connection.close();
	}

}
