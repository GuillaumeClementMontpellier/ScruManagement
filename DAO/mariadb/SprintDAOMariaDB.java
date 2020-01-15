package DAO.mariadb;

import DAO.SprintDAO;
import business.system.Project;
import business.system.Sprint;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Date start = resultSet.getDate("start");
        Date end = resultSet.getDate("end");
        return new Sprint(idSprint, idProject, start, end);
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
        sql = "INSERT INTO Sprint(idProject, idSprint, start, end) VALUES (?,?,?,?)";
        pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idSprint);
        pre.setInt(2, idProject);
        pre.setDate(3, start);
        pre.setDate(4, end);
        pre.execute();

        return new Sprint(idSprint, idProject, start, end);
    }

    @Override
    public boolean updateSprint(Sprint sprint) throws SQLException {
        String sql = "UPDATE Sprint SET  start = ?, end = ? WHERE id Sprint = ? && idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setDate(1, sprint.getStart());
        pre.setDate(2, sprint.getEnd());
        pre.setInt(3, sprint.getIdSprint());
        pre.setInt(4, sprint.getIdProject());
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

    @Override
    public Sprint[] getSprintsByProject(Project project) throws SQLException {
        String sql = "Select * FROM Sprint WHERE idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, project.getId());
        ResultSet resultSet = pre.executeQuery();
        ArrayList<Sprint> sprints = new ArrayList<>();
        while (resultSet.next()) {
            int idSprint = resultSet.getInt("idSprint");
            int idProject = resultSet.getInt("idProject");
            Date start = resultSet.getDate("start");
            Date end = resultSet.getDate("end");
            sprints.add(new Sprint(idSprint, idProject,  start, end));
        }
        return sprints.toArray(new Sprint[0]);
    }
}
