package DAO.mariadb;

import DAO.ProjectDAO;
import DAO.SprintDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Project;
import business.system.Sprint;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProjectDAOMariaDBTest {

    // prerequesite: getProjectByID working
    @Test
    public void createProject() {
        try {
            Date date = new Date(2020,01,14);
            ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
            Project project = projectDAO.createProject("TEST", "TestTestTest","Test", date);

            Project dbProject = projectDAO.getProjectByID(project.getId());
            if (dbProject != null) {
                System.out.println("createProject: Success");
            }
            else if (project == null) {
                System.out.println("createProject: Failed, function tells us something went wrong during the procedure");
            }
            else {
                System.out.println("createProject: Failed, either there is no project created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("createProject: Failed, Database ERROR at the execution");
        }
    }
}