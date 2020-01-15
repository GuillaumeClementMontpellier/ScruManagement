package business.facade;

import DAO.TicketDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Column;
import business.system.Project;
import business.system.Ticket;
import business.system.TicketBacklog;

import java.sql.SQLException;

public class TicketFacade {

    private GlobalFacade globalFacade;

    public void setGlobalFacade(GlobalFacade globalFacade) {
        this.globalFacade = globalFacade;
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
        TicketBacklog ticketBacklog = globalFacade.getTicketBacklog(project);
        Column column = globalFacade.getColumn(ticketBacklog)[0];

        // Insert Column Ticket
        success = globalFacade.addComponent(newTicket,column);

        return success;
    }

    public boolean deleteTicket(Ticket oldTicket) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.deleteTicket(oldTicket.getId());
    }

    public boolean updateTicket(Ticket updatedTicket, Ticket oldTicket) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.updateTicket(updatedTicket, oldTicket.getId());
    }

}
