package business.facade;

import DAO.BacklogDAO;
import DAO.ProjectDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Collaborator;
import business.system.Project;
import business.system.TicketBacklog;
import business.system.User;
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



    public Pair<List<User>, List<String>> getProjectTeam(Project project) throws SQLException {
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



    public void createProject(Project project, List<Collaborator> collaborators) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        // Project
        projectDAO.createProject(project.getId(), project.getName(), project.getSummary(), project.getType(), project.getDeadline());

        // Team
        for (Collaborator collaborator : collaborators ) {
            projectDAO.addCollaborator(collaborator.getIdProject(), collaborator.getIdUser(), collaborator.getIdRole(), collaborator.isAdmin());
        }
    }

    public void editProject(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        projectDAO.editProject(project);
    }

    public void deleteProject(Project project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        projectDAO.deleteProject(project.getId());
    }



    public void addCollaborator(Collaborator collaborator) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        projectDAO.addCollaborator(collaborator.getIdProject(), collaborator.getIdUser(), collaborator.getIdRole(), collaborator.isAdmin());
    }

    public void editCollaborator(Collaborator collaborator) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        projectDAO.editCollaborator(collaborator.getIdProject(), collaborator.getIdUser(), collaborator.getIdRole(), collaborator.isAdmin());
    }

    public void removeCollaborator(Collaborator collaborator) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        projectDAO.removeCollaborator(collaborator.getIdProject(), collaborator.getIdUser());
    }
}