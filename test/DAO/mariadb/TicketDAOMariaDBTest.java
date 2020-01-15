package DAO.mariadb;

import DAO.TicketDAO;
import DAO.UserStoryDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.UserStory;
import org.junit.Test;

import java.sql.SQLException;
import business.system.Ticket;

import static org.junit.Assert.*;

public class TicketDAOMariaDBTest {

    // prerequesite: getTicketById working
    @Test
    public void addTicket() {
        Ticket ticket = new Ticket(8080, "TicketTest", "TestTestTest", "inTest", 888);
        try {
            TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();
            boolean success = ticketDAO.addTicket(ticket, 1);

            Ticket dbTicket = ticketDAO.getTicketById(8080);
            if (ticket.getId() == dbTicket.getId()) {
                System.out.println("addTicket: Success");
            }
            else if (!success) {
                System.out.println("addTicket: Failed, function tells us something went wrong during the procedure");
            }
            else {
                System.out.println("addTicket: Failed, either there is no ticket created or an incorrect one");
            }
        } catch (SQLException e) {
            System.out.println("addTicket: Failed, Database ERROR at the execution");
        }
    }
}