package DAO.mariadb;

import DAO.TicketDAO;
import business.system.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketDAOMariaDB extends DAOMariaDB implements TicketDAO {

    public TicketDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public Ticket[] getTickets(Ticket ticket) throws SQLException {
        throw new SQLException("Uninplemented");
    }

    public Ticket getTicketById(int ticketId) throws SQLException {
        String sql = "Select * from Ticket " +
                "Where idTicket = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, ticketId);

        ResultSet resultSet = pre.executeQuery();

        boolean success = resultSet.first();

        if (!success) {
            return null;
        }

        String titleTicket = resultSet.getString("nameTicket");
        String descriptionTicket = resultSet.getString("descriptionTicket");
        String statusTicket = resultSet.getString("statusTicket");

        return new Ticket(ticketId, titleTicket, descriptionTicket, statusTicket);
    }

    public boolean addTicket(Ticket newTicket, int projectId) throws SQLException {

        // Insert Ticket
        boolean success = insertTicket(newTicket);
        if (!success) {
            return false;
        }

        // Search Column to Insert into
        int idColumn = getTicketBacklogColumn(projectId);
        if (idColumn == -1) {
            return false;
        }

        // Insert Column Ticket
        String sql = "INSERT INTO ColumnTicket" +
                "Values (?, ?)";

        PreparedStatement pre = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pre.setInt(1, idColumn);
        pre.setInt(2, newTicket.getIdTicket());

        int nbAffected = pre.executeUpdate();

        ResultSet rs = pre.getGeneratedKeys();

        if (rs.next()) {
            newTicket.setIdTicket(rs.getInt(1));
        }

        return nbAffected > 0;
    }

    public boolean deleteTicket(int ticketId) throws SQLException {

        String sql = "DELETE from ColumnTicket where idComponent = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, ticketId);

        int nbAff = pre.executeUpdate();

        sql = "DELETE from Ticket where idTicket = ?";

        pre = this.connection.prepareStatement(sql);

        pre.setInt(1, ticketId);

        int nbAffected = pre.executeUpdate();

        return (nbAffected > 0) && (nbAff > 0);
    }

    public boolean updateTicket(Ticket updatedTicket, int ticketId) throws SQLException {
        String sql = "UPDATE Ticket " +
                "SET nameTicket = ? " +
                "and descriptionTicket = ? " +
                "and statusTicket = ? " +
                "where idUserStory = ?";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setString(1, updatedTicket.getTitleTicket());
        pre.setString(2, updatedTicket.getDescriptionTicket());
        pre.setString(3, updatedTicket.getStatusTicket());
        pre.setInt(4, ticketId);

        int nbAffected = pre.executeUpdate();

        return nbAffected > 0;
    }

    private int getTicketBacklogColumn(int projectId) throws SQLException {
        // TODO : put real type
        String sql = "Select c.idColumn from Backlog b, ColumnBacklog c " +
                "WHERE b.idProject = ? " +
                "and b.typeBacklog = 2 " +
                "and b.idBacklog = c.idBacklog";

        PreparedStatement pre = this.connection.prepareStatement(sql);

        pre.setInt(1, projectId);

        ResultSet rs = pre.executeQuery();

        int idColumn = -1;
        if (rs.next()) {
            idColumn = rs.getInt(1);
        }
        return idColumn;
    }

    private boolean insertTicket(Ticket newTicket) throws SQLException {
        String sql = "Insert into Ticket " +
                "values ( ?, ?, ?)";

        PreparedStatement pre = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pre.setString(1, newTicket.getTitleTicket());
        pre.setString(2, newTicket.getDescriptionTicket());
        pre.setString(3, newTicket.getStatusTicket());

        int nbAffected = pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();


        if (nbAffected > 0) {
            return false;
        }

        int idTicket = -1;
        if (rs.next()) {
            idTicket = rs.getInt(1);
        } else {
            return false;
        }

        newTicket.setId(idTicket);
        return true;
    }
}
