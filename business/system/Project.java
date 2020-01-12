package business.system;

import java.sql.Date;

public class Project {
    private int id;
    private String name;
    private String summary;
    private String type;
    private Date deadline;

    public Project(int id, String name, String summary, String type, Date deadline) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.type = type;
        this.deadline = deadline;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}