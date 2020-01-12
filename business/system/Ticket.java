package business.system;

public class Ticket extends Component {

	private int idTicket;
	private String titleTicket;
    private String descriptionTicket;
	private String statusTicket;

	public Ticket(){
	
	}

	public Ticket(int id, String title, String description, String status) {		
		setIdTicket(id);		
		setTitleTicket(title);
		setDescriptionTicket(description);
		setStatusTicket(status);
	}

	public int getIdTicket() {
		return this.idTicket;	
	}

	public void setIdTicket(int id) {
		this.idTicket = id;	
	}

	public String getTitleTicket() {
		return this.titleTicket;
	}

	public void setTitleTicket(String title) {
		this.titleTicket = title;
	}
	
	public String getDescriptionTicket() {
		return this.descriptionTicket;
	}

	public void setDescriptionTicket(String description) {
		this.descriptionTicket = description;
	}

	public String getStatusTicket() {
		return this.statusTicket;
	}

	public void setStatusTicket(String status) {
		this.statusTicket = status;
	}

}
