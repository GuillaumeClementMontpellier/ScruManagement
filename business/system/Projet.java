package business.system;

public class Projet {
    private String name;
    private int id;

    public Projet(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}