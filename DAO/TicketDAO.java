package DAO;

import business.system.Ticket;

import java.sql.SQLException;

public interface TicketDAO {

    Tickets[] getTickets(Ticket ticket) throws SQLException;

    Ticket getTicketById(int ticketId) throws  SQLException;

    boolean addTicket(Ticket newTicket, int projectId) throws SQLException;

    boolean deleteTicket(int ticketId) throws SQLException;

    boolean updateTicket(Ticket updatedTicket, int ticketId) throws SQLException;
}
