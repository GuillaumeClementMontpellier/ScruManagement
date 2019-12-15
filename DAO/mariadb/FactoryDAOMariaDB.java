package DAO.mariadb;

import DAO.factory.AbstractFactoryDAO;

import java.sql.SQLException;

public class FactoryDAOMariaDB extends AbstractFactoryDAO {

	private String pwDataBase;
	private String userDataBase;
	private String urlDataBase;

	public FactoryDAOMariaDB(String urlDataBase, String userDataBase, String pwDataBase) {

		this.urlDataBase = urlDataBase;
		this.userDataBase = userDataBase;
		this.pwDataBase = pwDataBase;

	}
	
	public UserDAOMariaDB createUserDAO() throws SQLException {

		return new UserDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
	}
}
