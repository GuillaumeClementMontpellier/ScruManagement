package business.system;

import java.sql.Date;

public class UserStory extends Component {

    private int projetID;
    private int score;
    private Date deadline;

    public UserStory(int id, String description, int projetID, int score, Date deadline) {
        super(id,description);
        this.projetID = projetID;
        this.score = score;
        this.deadline = deadline;
    }



    public int getProjetID() {
        return projetID;
    }

    public void setProjetID(int projetID) {
        this.projetID = projetID;
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