package business.system;

import java.util.Date;

public class SprintBacklog extends Backlog {

    private Date startDate;
    private Date endDate;

    public SprintBacklog(int id) {
        super(id);
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