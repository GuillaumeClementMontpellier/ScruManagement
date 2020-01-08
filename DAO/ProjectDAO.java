package DAO;

import business.system.Projet;

import java.sql.SQLException;

public interface ProjectDAO {
    Projet getProjetByID(int idProject) throws SQLException;


}
