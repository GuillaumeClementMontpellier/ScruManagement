package business.system;

public class Column implements Commentable, Comparable<Column> {

    private int id;
    private String name;
    private int rank;
    private String description;

    public Column(int id, String name, int rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    public int getId() {
        return id;
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

    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Comment getComments() {
        // TODO
        return null;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Column column) {
        return this.getRank() - column.getRank();
    }
}