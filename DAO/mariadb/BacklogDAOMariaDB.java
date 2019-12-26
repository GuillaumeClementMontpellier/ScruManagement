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

public class BacklogDAOMariaDB extends BacklogDAO {

    public BacklogDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public ProductBacklog getProductBacklog(int idProject) throws SQLException {
        ResultSet resultSet = getBacklog(idProject, "1");
        int id = resultSet.getInt("idBacklog");
        return new ProductBacklog(id);

    }

    @Override
    public TicketBacklog getTicketBacklog(int idProject) throws SQLException {
        ResultSet resultSet = getBacklog(idProject, "2");
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
    public SprintBacklog[] getAllSprintBacklog(int idProject) {
        //TODO
        return new SprintBacklog[0];
    }

    @Override
    public Column[] getColumn(Backlog backlog) throws SQLException {
        String sql = "Select idColumn, name From backlog where idBacklog = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, backlog.getId());
        ResultSet resultSet = pre.executeQuery();

        ArrayList<Column> solution = new ArrayList();
        int id;
        String name;
        while (resultSet.next()) {
            id = resultSet.getInt("idColumn");
            name = resultSet.getString("name");
            solution.add(new Column(id, name));
        }
        Column[] soos = solution.toArray(new Column[solution.size()]);
        return soos;
    }

    @Override
    public UserStory[] getUserStory(Column col) throws SQLException {
        //TODO
        return new UserStory[0];
    }

    private ResultSet getBacklog (int idProject, String type) throws SQLException {
        String sql = "Select idBacklog From  backlog where idProject = ? and type = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        pre.setString(2,type);
        return pre.executeQuery();
    }
}
