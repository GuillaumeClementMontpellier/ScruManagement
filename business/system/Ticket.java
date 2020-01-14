package business.system;


public class Ticket extends Component {

    private int idTicket;
    private String titleTicket;
    private String descriptionTicket;
    private String statusTicket;

    public Ticket(int id, String title, String description, String status) {
        setId(id);
        setName(title);
        setDescription(description);
        setStatusTicket(status);
    }

    public int getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getTitleTicket() {
        return this.titleTicket;
    }

    public void setTitleTicket(String titleTicket) {
        this.titleTicket = titleTicket;
    }

    public String getDescriptionTicket() {
        return this.descriptionTicket;
    }

    public void setDescriptionTicket(String descriptionTicket) {
        this.descriptionTicket = descriptionTicket;
    }

    public String getStatusTicket() {
        return this.statusTicket;
    }

    public void setStatusTicket(String status) {
        this.statusTicket = status;
    }

}
