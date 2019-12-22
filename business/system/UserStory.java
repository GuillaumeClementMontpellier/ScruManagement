package business.system;

import java.sql.Date;

public class UserStory extends Component {

    private int id;
    private int projetID;
    private int score;
    private Date deadline;
    private String description;

    public UserStory(int id, String description, int projetID, int score, Date deadline) {
        this.id = id;
        this.projetID = projetID;
        this.score = score;
        this.deadline = deadline;
        this.description = description;
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}