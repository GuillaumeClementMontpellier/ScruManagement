package DAO;

import business.system.Projet;
import business.system.User;

import java.sql.SQLException;

public interface ProjectDAO {
    Projet getProjectByID(int idProject) throws SQLException;


    User getProjectAdmin(int idProject) throws SQLException;

    User getProjectScrumMaster(int idProject) throws SQLException;

    User getProjectProductOwner(int idProject) throws SQLException;

    User[] getProjectDevelopers(int idProject) throws SQLException;

    User[] getProjectTeam(int idProject) throws SQLException;


    void createProject(Projet projet) throws SQLException;

    void editProject(Projet projet) throws SQLException;

    void deleteProject(Projet projet) throws SQLException;

    void quitProject(int idProject, User collaborator) throws SQLException;


    void addCollaborator(int idProject, User collaborator) throws SQLException;

    void editCollaborator(int idProject, User collaborator) throws SQLException;

    void removeCollaborator(int idProject, User collaborator) throws SQLException;
}
