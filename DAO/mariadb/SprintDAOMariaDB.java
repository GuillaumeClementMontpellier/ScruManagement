package DAO.mariadb;

import DAO.SprintDAO;
import business.system.Project;
import business.system.Sprint;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SprintDAOMariaDB extends DAOMariaDB implements SprintDAO {

    public SprintDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    @Override
    public Sprint getSprintById(int idSprint, int idProject) throws SQLException {
        String sql = "Select * FROM Sprint WHERE idSprint = ? && idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idSprint);
        pre.setInt(2, idProject);
        ResultSet resultSet = pre.executeQuery();
        String type = resultSet.getString("type");
        Date start = resultSet.getDate("start");
        Date end = resultSet.getDate("end");
        return new Sprint(idSprint, idProject, type, start, end);
    }

    @Override
    public String getTypeOfSprint(Sprint sprint) throws SQLException {
        String sql = "Select type FROM Sprint WHERE idSprint = ? && idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, sprint.getIdSprint());
        pre.setInt(2, sprint.getIdProject());
        ResultSet resultSet = pre.executeQuery();
        String type = resultSet.getString("type");
        return type;
    }

    @Override
    public Sprint createSprint(int idProject, String type, Date start, Date end) throws SQLException {
        // Determination of idSprint
        String sql = "Select idSprint, idProject FROM Sprint WHERE idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        ResultSet resultSet = pre.executeQuery();
        int idSprint = 0;
        while (resultSet.next()) {
            idSprint +=1;
        }

        // Inserting sprint
        sql = "INSERT INTO Project VALUES (?,?,?,?)";
        pre = this.connection.prepareStatement(sql);
        pre.setDate(1, start);
        pre.setDate(2, end);
        pre.setInt(3, idSprint);
        pre.setInt(4, idProject);
        pre.execute();

        return new Sprint(idSprint, idProject, type, start, end);
    }

    @Override
    public boolean updateSprint(Sprint sprint) throws SQLException {
        String sql = "UPDATE Sprint SET type = ?, start = ?, end = ? WHERE id Sprint = ? && idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setString(1, sprint.getType());
        pre.setDate(2, sprint.getStart());
        pre.setDate(3, sprint.getEnd());
        pre.setInt(4, sprint.getIdSprint());
        pre.setInt(5, sprint.getIdProject());
        pre.execute();
        return true;
    }

    @Override
    public boolean deleteSprint(Sprint sprint) throws SQLException {
        String sql = "DELETE FROM Sprint WHERE idSprint = ? && idProject = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, sprint.getIdSprint());
        pre.setInt(2, sprint.getIdProject());
        pre.execute();
        return true;
    }
}
