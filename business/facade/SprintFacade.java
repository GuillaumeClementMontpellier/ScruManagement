package business.facade;

import DAO.SprintDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Project;
import business.system.Sprint;

import java.sql.Date;
import java.sql.SQLException;

public class SprintFacade {

    public Sprint getSprintById(int idSprint, Project project) throws SQLException {
        SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
        return sprintDAO.getSprintById(idSprint, project.getId());
    }

    public Sprint createSprint(Project project, String type , Date start, Date end) throws SQLException {
        SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
        return sprintDAO.createSprint(project.getId(), type, start, end);
    }

    public boolean updateSprint(Sprint sprint) throws SQLException {
        SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
        return sprintDAO.updateSprint(sprint);
    }

    public boolean deleteSprint(Sprint sprint) throws SQLException {
        SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
        return sprintDAO.deleteSprint(sprint);
    }

    public Sprint[] getSprintsByProject(Project project) throws SQLException {
        SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
        return sprintDAO.getSprintsByProject(project);
    }
}
