package business.system;

import java.sql.Date;

public class UserStory extends Component {

    private int score;
    private Date deadline;

    public UserStory() {
    }

    public UserStory(int id, String name, String description, int score, Date deadline) {
        setId(id);
        setName(name);
        setDescription(description);
        setScore(score);
        setDeadline(deadline);
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


}