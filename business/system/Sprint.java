package business.system;

import java.sql.Date;

public class Sprint {
    private int idSprint;
    private int idProject;
    private Date start;
    private Date end;

    public Sprint(int idSprint, int idProject,  Date start, Date end) {
        this.idSprint = idSprint;
        this.idProject = idProject;
        this.start = start;
        this.end = end;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}