package DAO.mariadb;

import DAO.SprintDAO;
import DAO.TicketDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Sprint;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class SprintDAOMariaDBTest {

    // prerequesite: getSprintById working
    @Test
    public void createSprint() {
        try {
            Date start = new Date(2020,01,14);
            Date end = new Date(2020,01,14);
            SprintDAO sprintDAO = AbstractFactoryDAO.getInstance().createSprintDAO();
            Sprint sprint = sprintDAO.createSprint(8080, "Test",start, end);

            Sprint dbSprint = sprintDAO.getSprintById(sprint.getIdSprint(), sprint.getIdProject());
            if (dbSprint != null) {
                System.out.println("createSprint: Success");
            }
            else if (sprint == null) {
                System.out.println("createSprint: Failed, function tells us something went wrong during the procedure");
            }
            else {
                System.out.println("createSprint: Failed, either there is no sprint created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("createSprint: Failed, Database ERROR at the execution");
        }
    }
}