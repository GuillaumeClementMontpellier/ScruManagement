package DAO.mariadb;

import DAO.TicketDAO;
import business.system.Ticket;
import business.system.UserStory;

import java.sql.*;

public class TicketDAOMariaDB extends DAOMariaDB implements TicketDAO {

    public TicketDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public Ticket getTicketById(int ticketId) throws  SQLException {

    }

    public boolean addTicket(Ticket newTicket, int projectId) throws SQLException {

    }

    public boolean deleteTicket(int ticketId) throws SQLException {

    }

    public boolean updateTicket(Ticket updatedTicket, int ticketId) throws SQLException {

    }

}
