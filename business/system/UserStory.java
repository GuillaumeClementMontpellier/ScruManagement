package business.system;

import java.sql.Date;

public class UserStory extends Component {

    private int score;
    private Date deadline;
    private int columnId;

    public UserStory(int id, String name, String description, int score, Date deadline) {
        setId(id);
        setName(name);
        setDescription(description);
        setScore(score);
        setDeadline(deadline);
    }

    @Override
    public String toString() {
        return getName() + " : " + getId();
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }
}