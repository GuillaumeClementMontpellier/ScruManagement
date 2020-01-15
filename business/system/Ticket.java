package business.system;


public class Ticket extends Component {

    private String statusTicket;
    private int idUserStory;
    private int idColumn;

    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
    }

    public Ticket(int id, String title, String description, String status, int userStory) {
        setId(id);
        setName(title);
        setDescription(description);
        setStatusTicket(status);
        setUserStory(userStory);
    }

    public static String[] getStatus() {
        String[] strings = new String[3];
        strings[0] = "Unsolved";
        strings[1] = "WIP";
        strings[2] = "Solved";
        return strings;
    }

    public int getUserStory() {
        return idUserStory;
    }

    public void setUserStory(int userStory) {
        this.idUserStory = userStory;
    }

    public String getStatusTicket() {
        return this.statusTicket;
    }

    public void setStatusTicket(String status) {
        this.statusTicket = status;
    }

}
