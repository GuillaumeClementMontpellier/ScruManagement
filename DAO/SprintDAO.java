package DAO;

import business.system.Sprint;

import java.sql.SQLException;
import java.sql.Date;

public interface SprintDAO {

    Sprint getSprintById(int idSprint, int idProject) throws SQLException;

    String getTypeOfSprint(Sprint sprint) throws SQLException;

    Sprint createSprint(int idProject, String type , Date start, Date end) throws SQLException;

    boolean updateSprint(Sprint sprint) throws SQLException;

    boolean deleteSprint(Sprint sprint) throws SQLException;
}
