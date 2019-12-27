package business.system;

public abstract class Component implements Commentable {

    private int id;
    private String name;
    private String description;

    public Component(int id,String description){
        this.id = id;
        this.description = description;
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

}