package business.system;


public class Ticket extends Component {

    private String statusTicket;
    private UserStory userStory;

    public Ticket(int id, String title, String description, String status, UserStory userStory) {
        setId(id);
        setName(title);
        setDescription(description);
        setStatusTicket(status);
        setUserStory(userStory);
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public String getStatusTicket() {
        return this.statusTicket;
    }

    public void setStatusTicket(String status) {
        this.statusTicket = status;
    }

}
