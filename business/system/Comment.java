package business.system;

public class Comment {

    private int id;
    private int proprietary;
    private String description;

    public Comment(int id, int proprietary, String description){
        this.id = id;
        this.proprietary = proprietary;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getProprietary() {
        return proprietary;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}