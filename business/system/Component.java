package business.system;

public abstract class Component implements Commentable {

    private int id;

    private String name;
    private String description;

    public Component(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Component() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Comment getComments() {
        // Todo
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}