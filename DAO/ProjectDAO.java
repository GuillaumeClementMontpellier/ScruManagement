package DAO;

import business.system.Collaborator;
import business.system.Project;
import business.system.User;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProjectDAO {
    List<Project> getProjectListFromUser(int idUser) throws SQLException;

    Project getProjectByID(int idProject) throws SQLException;


    // User & their Role on the project
    List<Collaborator> getProjectTeam(int idProject) throws SQLException;

    User getProjectAdmin(int idProject) throws SQLException;

    User getProjectScrumMaster(int idProject) throws SQLException;

    User getProjectProductOwner(int idProject) throws SQLException;

    List<User> getProjectDevelopers(int idProject) throws SQLException;


    void createProject(int id, String name, String summary, String type, Date deadline) throws SQLException;

    void editProject(Project project) throws SQLException;

    void deleteProject(int idProject) throws SQLException;


    void addCollaborator(int idProject, int idCollaborator, int role, boolean isAdmin) throws SQLException;

    void editCollaborator(int idProject, int idCollaborator, int role, boolean isAdmin) throws SQLException;

    void removeCollaborator(int idProject, int collaborator) throws SQLException;
}
