package business.facade;

import DAO.ProjectDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ProjectFacade {
    public List<Projet> getProjectListFromUser(User user) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectListFromUser(user.getId());
    }

    public Projet getProjectByID(int idProject) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectByID(idProject);
    }



    public List<Collaborator> getProjectTeam(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectTeam(project.getId());
    }

    public User getProjectAdmin(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectAdmin(project.getId());
    }

    public User getProjectScrumMaster(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectScrumMaster(project.getId());
    }

    public User getProjectProductOwner(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectProductOwner(project.getId());
    }

    public List<User> getProjectDevelopers(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.getProjectDevelopers(project.getId());
    }



    public Projet createProject(String name, String summary, String type, Date deadline) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.createProject(name, summary, type, deadline);
    }

    public boolean editProject(Projet project) throws SQLException {
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        return projectDAO.editProject(project);
    }

    public boolean deleteProject(Projet project) throws SQLException {
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