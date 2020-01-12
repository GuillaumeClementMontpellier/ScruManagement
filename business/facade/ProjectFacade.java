package business.facade;

import DAO.BacklogDAO;
import DAO.ProjectDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.*;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectFacade {
    public List<Project> getProjectListFromUser(User user) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectListFromUser(user.getId());
    }

    public Project getProjectByID(int idProject) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectByID(idProject);
    }



    public List<Collaborator> getProjectTeam(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectTeam(project.getId());
    }

    public User getProjectAdmin(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectAdmin(project.getId());
    }

    public User getProjectScrumMaster(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectScrumMaster(project.getId());
    }

    public User getProjectProductOwner(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectProductOwner(project.getId());
    }

    public List<User> getProjectDevelopers(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectDevelopers(project.getId());
    }



    public Project createProject(String name, String summary, String type, Date deadline) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.createProject(name, summary, type, deadline);
    }

    public boolean editProject(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.editProject(project);
    }

    public boolean deleteProject(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.deleteProject(project.getId());
    }



    public Collaborator addCollaborator(int idProject, int idCollaborator, int idRole, boolean isAdmin) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.addCollaborator(idProject, idCollaborator, idRole, isAdmin);
    }

    public boolean editCollaborator(Collaborator collaborator) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.editCollaborator(collaborator.getIdProject(), collaborator.getIdUser(), collaborator.getIdRole(), collaborator.isAdmin());
    }

    public boolean removeCollaborator(Collaborator collaborator) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.removeCollaborator(collaborator.getIdProject(), collaborator.getIdUser());
    }
}