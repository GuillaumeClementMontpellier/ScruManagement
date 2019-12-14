package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

abstract class UserDAO {
    private Connection connection;
    private Statement;
    public UserDAO(String addressDataBase, String userDataBase, String passWordDataBase){
        this.connection = DriverManager.getConnection(addressDataBase, userDataBase, passWordDataBase);
    }


}
