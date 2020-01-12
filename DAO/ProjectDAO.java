package DAO;

import business.system.Projet;
import business.system.User;

import java.sql.SQLException;

public interface ProjectDAO {
    Projet[] getProjectListFromUser(User u) throws SQLException;
    Projet getProjectByID(int idProject) throws SQLException;

    User getProjectAdmin(int idProject) throws SQLException;

    User getProjectScrumMaster(int idProject) throws SQLException;

    User getProjectProductOwner(int idProject) throws SQLException;

    User[] getProjectDevelopers(int idProject) throws SQLException;

    User[] getProjectTeam(int idProject) throws SQLException;


    boolean createProject(Projet projet, User creator) throws SQLException;

    boolean editProject(Projet projet) throws SQLException;

    boolean deleteProject(Projet projet) throws SQLException;


    boolean addCollaborator(int idProject, User collaborator) throws SQLException;

    boolean editCollaborator(int idProject, User collaborator) throws SQLException;

    boolean removeCollaborator(int idProject, User collaborator) throws SQLException;
}
