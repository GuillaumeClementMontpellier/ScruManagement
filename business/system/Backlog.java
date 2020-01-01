package business.system;

public abstract class Backlog implements Commentable {

    private int id;

    private String name;

    public Backlog(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}