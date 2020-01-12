package business.facade;

import DAO.TicketDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.Ticket;

import java.sql.SQLException;

public class TicketFacade {

    public Ticket getTicketById(int ticketId) throws  SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.getTicketById(ticketId);
    }

    public boolean addTicket(Ticket newTicket, int ProjectId) throws SQLException {
        TicketDAO ticketDAO = AbstractFactoryDAO.getInstance().createTicketDAO();

        return ticketDAO.updateTicket(newTicket, ProjectId);
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
