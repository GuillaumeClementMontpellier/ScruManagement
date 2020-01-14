package business.facade;

import DAO.TicketDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Column;
import business.system.Project;
import business.system.Ticket;
import business.system.TicketBacklog;

import java.sql.SQLException;

public class TicketFacade {

    private BacklogFacade backlogFacade;

    public void setBacklogFacade(BacklogFacade backlogFacade) {
        this.backlogFacade = backlogFacade;
    }

    public Ticket getTicketById(int ticketId) throws  SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.getTicketById(ticketId);
    }

    public boolean addTicket(Ticket newTicket, Project project) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        boolean success = ticketDAO.addTicket(newTicket, project.getId());
        if (!success) {
            return false;
        }

        // Search Column to Insert into
        TicketBacklog ticketBacklog = backlogFacade.getTicketBacklog(project);
        Column[] column = backlogFacade.getColumn(ticketBacklog);

        // Insert Column Ticket
        success = backlogFacade.addComponent(newTicket,column[0]);

        return success;
    }

    public boolean deleteTicket(Ticket oldTicket) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        boolean success = ticketDAO.deleteTicket(oldTicket.getId());
        return success;
    }

    public boolean updateTicket(Ticket updatedTicket, Ticket oldTicket) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.updateTicket(updatedTicket, oldTicket.getId());
    }

}
