package business.system;


public class Ticket extends Component {

    private String statusTicket;
    private int idUserStory;

    public Ticket(int id, String title, String description, String status, int userStory) {
        setId(id);
        setName(title);
        setDescription(description);
        setStatusTicket(status);
        setUserStory(userStory);
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
