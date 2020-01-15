package DAO.mariadb;

import DAO.TicketDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Backlog;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class BacklogDAOMariaDBTest {

    // prerequesite: getLatestSprintBacklog working
    @Test
    public void createSprintBacklog() {
        try {
            Date start = new Date(2020,01,14);
            Date end = new Date(2020,01,14);
            BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
            boolean success = backlogDAO.createSprintBacklog(8080, start, end);

            Backlog dbBacklog = backlogDAO.getLatestSprintBacklog(8080);
            if (dbBacklog != null) {
                System.out.println("createSprintBacklog: Success");
            }
            else if (!success) {
                System.out.println("createSprintBacklog: Failed, function tells us something went wrong during the procedure");
            }
            else {
                System.out.println("createSprintBacklog: Failed, either there is no sprint backlog created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("createSprintBacklog: Failed, Database ERROR at the execution");
        }
    }
}