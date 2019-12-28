package business.system;

import java.sql.Date;

public class UserStory extends Component {

    private int id;
    private int projetID;
    private int score;
    private Date deadline;

    public UserStory() {
    }

    public UserStory(int id, String name, String description, int projetID, int score, Date deadline) {
        setId(id);
        setName(name);
        setDescription(description);
        setProjetID(projetID);
        setScore(score);
        setDeadline(deadline);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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