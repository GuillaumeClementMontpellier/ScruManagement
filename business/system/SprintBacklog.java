package business.system;

import java.sql.Date;

public class SprintBacklog extends Backlog {

    private Date startDate;
    private Date endDate;

    public SprintBacklog(int id) {
        super(id);
    }

    public SprintBacklog(int id, java.sql.Date startDate, java.sql.Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date endDate) {
        this.endDate = endDate;
    }
    @Override
    public Comment getComments() {
        // Todo
        return null;
    }
}