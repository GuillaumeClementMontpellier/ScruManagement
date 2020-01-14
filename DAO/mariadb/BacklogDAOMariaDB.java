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
        boolean first = resultSet.first();
        if (!first) {
            return null;
        }
        System.out.println(resultSet.getRow());
        int id = resultSet.getInt("idBacklog");
        return new ProductBacklog(id);

    }

    @Override
    public TicketBacklog getTicketBacklog(int idProject) throws SQLException {
        ResultSet resultSet = getBacklog(idProject, 2);
        boolean first = resultSet.first();
        if (!first) {
            return null;
        }
        int id = resultSet.getInt("idBacklog");
        return new TicketBacklog(id);
    }

    @Override
    public SprintBacklog getLatestSprintBacklog(int idProject) throws SQLException {
        String sql = "Select idBacklog From Backlog where idProject = ? and typeBacklog = ? order by idBacklog Desc";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, Integer.toString(idProject));
        pre.setInt(2, 3);
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
        Date start = resultSet.getDate("beginDate");
        Date end = resultSet.getDate("endDate");
        bc.setStartDate(start);
        bc.setEndDate(end);


        return bc;
    }

    @Override
    public SprintBacklog[] getAllSprintBacklog(int idProject) throws SQLException {
        //TODO

        String sql = "Select * From SprintBacklog Where idBacklog Exists(Select idBacklog From  backlog where idProject = ? and typeBacklog = ? order by idBacklog Desc)";
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
        String sql = "Select idColumn, name, rank From ColumnBacklog where idBacklog = ? order by rank";
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
            solution.add(new Column(id, name, rank));
        }
        Column[] soos = solution.toArray(new Column[solution.size()]);
        return soos;
    }

    @Override
    public UserStory[] getUserStory(Column col) throws SQLException {
        String sql = "Select * From UserStory where idUserStory in (Select idUserStory from ColumnUserStory where idColumn  =  ?)";
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
        ArrayList<Ticket> solution = new ArrayList<>();
        while (resultSet.next()) {

            int idTicket = resultSet.getInt("idTicket");
            String titleTicket = resultSet.getString("nameTicket");
            String descriptionTicket = resultSet.getString("descriptionTicket");
            String statusTicket = resultSet.getString("statusTicket");
            int idUserStory = resultSet.getInt("idUserStory");
            solution.add(new Ticket(idTicket, titleTicket, descriptionTicket, statusTicket, idUserStory));
        }
        Ticket[] soos = solution.toArray(new Ticket[solution.size()]);
        return soos;
    }

    @Override
    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        PreparedStatement pre = fillSQL("DELETE FROM", "WHERE idColumn = ? AND idComponent = ? ",
                c, fromCol);
        pre.execute();
        return addComponent(c, toCol);
    }

    @Override
    public boolean addComponent(Component c, Column col) throws SQLException {
        PreparedStatement pre = fillSQL("Insert INTO","Values (?,?)", c, col);
        int nbAffected = pre.executeUpdate();
        return nbAffected > 0;
    }

    @Override
    public boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException {
        createBacklog(idProject, 3);

        //TODO check if there is another way to do this:
        String sql = "SELECT LAST_INSERT_ID() FROM Backlog";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        boolean first = resultSet.first();
        if (!first) {
            return false;
        }
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
        String sql = "Insert into Backlog(idProject, typeBacklog) Values (?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pre.setInt(1, idProject);
        pre.setInt(2, type);
        pre.execute();

        ResultSet rs = pre.getGeneratedKeys();
        boolean first = rs.first();
        if (!first) {
            return;
        }

        int id = rs.getInt(1);

        sql = "Insert into ColumnBacklog(idBacklog, name, rank) " +
                "values (?, ?, ?),(?, ?,?),(?, ?,?);";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, id);
        pre.setString(2, "TO DO");
        pre.setInt(3, 1);

        pre.setInt(4, id);
        pre.setString(5, "WIP");
        pre.setInt(6, 2);

        pre.setInt(7, id);
        pre.setString(8, "DONE");
        pre.setInt(9, 3);

        pre.execute();
    }

    private PreparedStatement fillSQL(String part1, String part2, Component c, Column col) throws SQLException {
        PreparedStatement pre = setCorrectTable(part1, part2, c);
        pre.setInt(1, col.getId());
        pre.setInt(2, c.getId());
        return pre;
    }

    private PreparedStatement setCorrectTable(String part1, String part2, Component c) throws SQLException {
        // TODO : change : Bad smell !!
        String sql = part1;
        if (c instanceof Ticket) {
            sql += " ColumnTicket ";
        } else {
            sql += " ColumnUserStory(idColumn,idComponent) ";
        }
        sql += part2;
        PreparedStatement pre = this.connection.prepareStatement(sql);
        return pre;
    }

    private ResultSet getBacklog(int idProject, int type) throws SQLException {
        String sql = "Select idBacklog From Backlog where idProject = ? and typeBacklog = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        pre.setInt(2, type);
        return pre.executeQuery();
    }
}
