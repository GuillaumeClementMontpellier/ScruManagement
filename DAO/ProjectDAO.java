package DAO;

import business.system.Collaborator;
import business.system.Projet;
import business.system.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {
    List<Projet> getProjectListFromUser(int idUser) throws SQLException;

    Projet getProjectByID(int idProject) throws SQLException;


    // User & their Role on the project
    List<Collaborator> getProjectTeam(int idProject) throws SQLException;

    User getProjectAdmin(int idProject) throws SQLException;

    User getProjectScrumMaster(int idProject) throws SQLException;

    User getProjectProductOwner(int idProject) throws SQLException;

    List<User> getProjectDevelopers(int idProject) throws SQLException;


    Projet createProject(String name, String summary, String type, Date deadline) throws SQLException;

    boolean editProject(Projet project) throws SQLException;

    boolean deleteProject(int idProject) throws SQLException;


    Collaborator addCollaborator(int idProject, int idCollaborator, int role, boolean isAdmin) throws SQLException;

    boolean editCollaborator(int idProject, int idCollaborator, int role, boolean isAdmin) throws SQLException;

    boolean removeCollaborator(int idProject, int collaborator) throws SQLException;
}
