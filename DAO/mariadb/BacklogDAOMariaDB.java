package DAO.mariadb;

import DAO.BacklogDAO;
import business.system.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//type 1 = product
//type 2 = ticket
//type 3 = sprint

public class BacklogDAOMariaDB extends DAOMariaDB implements BacklogDAO {

    public BacklogDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public ProductBacklog getProductBacklog(int idProject) throws SQLException {
        ResultSet resultSet = this.getBacklog(idProject, 1);
        int id = resultSet.getInt("idBacklog");
        return new ProductBacklog(id);

    }

    @Override
    public TicketBacklog getTicketBacklog(int idProject) throws SQLException {
        ResultSet resultSet = getBacklog(idProject, 2);
        int id = resultSet.getInt("idBacklog");
        return new TicketBacklog(id);
    }

    @Override
    public SprintBacklog getLatestSprintBacklog(int idProject) throws SQLException {
        String sql = "Select idBacklog From  backlog where idProject = ? and type = ? order by idBacklog Desc";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, Integer.toString(idProject));
        pre.setString(2, "3");
        ResultSet resultSet = pre.executeQuery();

        boolean success = resultSet.first();

        if (!success) {
            return null;
        }

        int id = resultSet.getInt("idBacklog");
        SprintBacklog bc = new SprintBacklog(id);

        sql = "Select beginDate, endDate From SprintBacklog where idBacklog = ?";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        resultSet = pre.executeQuery();
        success = resultSet.first();

        if (success) {
            Date start = resultSet.getDate("beginDate");
            Date end = resultSet.getDate("endDate");
            bc.setStartDate(start);
            bc.setEndDate(end);
        }

        return bc;
    }

    @Override
    public SprintBacklog[] getAllSprintBacklog(int idProject) throws SQLException {
        //TODO

        String sql = "Select * From SprintBacklog Where idBacklog Exists(Select idBacklog From  backlog where idProject = ? and type = ? order by idBacklog Desc)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, Integer.toString(idProject));
        pre.setString(2, "3");
        ResultSet resultSet = pre.executeQuery();

        ArrayList<SprintBacklog> solution = new ArrayList<SprintBacklog>();
        int id;
        Date startDate;
        Date endDate;
        while (resultSet.next()) {
            id = resultSet.getInt("idBacklog");
            startDate = resultSet.getDate("beginDate");
            endDate = resultSet.getDate("endDate");
            solution.add(new SprintBacklog(id, startDate, endDate));
        }
        SprintBacklog[] soos = solution.toArray(new SprintBacklog[solution.size()]);
        return soos;
    }

    @Override
    public Column[] getColumn(Backlog backlog) throws SQLException {
        String sql = "Select idColumn, name From backlog where idBacklog = ? order by rank";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, backlog.getId());
        ResultSet resultSet = pre.executeQuery();

        ArrayList<Column> solution = new ArrayList();
        int id;
        String name;
        int rank;
        while (resultSet.next()) {
            id = resultSet.getInt("idColumn");
            name = resultSet.getString("name");
            rank = resultSet.getInt("rank");
            solution.add(new Column(id, name,rank));
        }
        Column[] soos = solution.toArray(new Column[solution.size()]);
        return soos;
    }

    @Override
    public UserStory[] getUserStory(Column col) throws SQLException {
        String sql = "Select * From UserStory where idUserStory EXISTS (Select idUserStory from ColumnUserStory where idColumn  =  ?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, col.getId());
        ResultSet resultSet = pre.executeQuery();

        ArrayList<UserStory> solution = new ArrayList();
        int id;
        int idProject;
        int score;
        Date deadline;
        String description;
        String name;
        while (resultSet.next()) {
            id = resultSet.getInt("idUserStory");
            name = resultSet.getString("nameUserStory");
            score = resultSet.getInt("score");
            deadline = resultSet.getDate("deadline");
            description = resultSet.getString("descriptionUserStory");
            solution.add(new UserStory(id, name, description, score, deadline));
        }
        UserStory[] soos = solution.toArray(new UserStory[solution.size()]);
        return soos;
    }

    @Override
    public Ticket[] getTickets(Column col) throws SQLException {
        String sql = "Select * From Ticket where idTicket EXISTS (Select idTicket from ColumnTicket where idColumn  =  ?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, col.getId());
        ResultSet resultSet = pre.executeQuery();

        ArrayList<Ticket> solution = new ArrayList();
        int idTicket;
        String titleTicket;
		String descriptionTicket;
		String statusTicket;
        while (resultSet.next()) {

            idTicket = resultSet.getInt("idTicket");
            titleTicket = resultSet.getString("nameTicket");
            descriptionTicket = resultSet.getString("descriptionTicket");
            statusTicket = resultSet.getString("statusTicket");
            solution.add(new Ticket(idTicket, titleTicket, descriptionTicket, statusTicket));
        }
        Ticket[] soos = solution.toArray(new Ticket[solution.size()]);
        return soos;
    }

    @Override
    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        String sql = "DELETE FROM ? WHERE idColumn = ? AND idComponent = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre = fillSQL(pre, c, fromCol);
        pre.execute();

        return addComponent(c, toCol);
    }

    @Override
    public boolean addComponent(Component c, Column col) throws SQLException {
        String sql = "Insert INTO ? Values (?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre = fillSQL(pre, c, col);
        pre.execute();
        return true;
    }

    @Override
    public boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException {
        createBacklog(idProject, 3);

        //TODO check if there is another way to do this:
        String sql = "SELECT LAST_INSERT_ID() FROM Backlog";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        int id = resultSet.getInt("LAST_INSERT_ID()");

        sql = "Insert INTO SprintBacklog Values (?,?,?)";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, id);
        pre.setDate(2, startDate);
        pre.setDate(3, endDate);
        pre.execute();
        return true;
    }

    @Override
    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        String sql = "Remove From SprintBacklog Where idBacklog = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, sprintBacklog.getId());
        pre.execute();
        sql = "Remove From Backlog Where idBacklog = ?";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, sprintBacklog.getId());
        pre.execute();
        return true;
    }

    @Override
    public boolean initiateProductTicketBacklog(int idProject) throws SQLException {
        createBacklog(idProject, 1);
        createBacklog(idProject, 2);
        return true;
    }


    private void createBacklog(int idProject, int type) throws SQLException {
        String sql = "Insert into Backlog(idProject, type) Values (?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pre.setInt(1, idProject);
        pre.setInt(2, type);
        pre.execute();

        ResultSet rs = pre.getGeneratedKeys();

        int id = rs.getInt(1);

        sql = "Insert into Column(idBacklog, name, rank) values (?, TO DO, 1)(?, Work in progress,2)(?, Done,3)";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, id);
        pre.setInt(2, id);
        pre.setInt(3, id);
        pre.execute();
    }

    private PreparedStatement fillSQL(PreparedStatement pre, Component c, Column col) throws SQLException {
        pre = setCorrectTable(pre, c);
        pre.setInt(2, col.getId());
        pre.setInt(3, c.getId());
        return pre;
    }

    private PreparedStatement setCorrectTable(PreparedStatement pre, Component c) throws SQLException {
        // TODO : change : Bad smell !!
        if (c instanceof Ticket) {
            pre.setString(1, "ColumnTicket");
        } else {
            pre.setString(1, "ColumnUserStory");
        }
        return pre;
    }

    private ResultSet getBacklog(int idProject, int type) throws SQLException {
        String sql = "Select idBacklog From  backlog where idProject = ? and type = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        pre.setInt(2, type);
        return pre.executeQuery();
    }
}
