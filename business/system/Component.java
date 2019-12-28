package business.system;

public abstract class Component implements Commentable {

    private String name;
    private String description;

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

}