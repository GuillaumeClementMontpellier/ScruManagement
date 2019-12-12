package FactoryDAOMariaDB;

public class FactoryDAOMariaDB {
	
	public FactoryDAOMariaDB() {
		
	}
	
	public UserDAOMariaDB createUserDAOMariaDB(String dataBase, String user, String password) {
		
		return UserDAOMariaDB(dataBase, user, password);
	}
}
