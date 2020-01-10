package business.system;

import java.text.SimpleDateFormat;  
import java.util.Date; 

public class Ticket extends Component {

	private String titleTicket;
    private String descriptionTicket;
	private String statusTicket;
	private Date creationDateTicket;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public Ticket(){
	
	}

	public Ticket(int id, String title, String description, String status) {		
		setTitleTicket(title);
		setDescription(description);
		setStatus(status);
		setCreationDate(formatter.format(java.time.LocalDate.now()));
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
		this.satusTicket = status;
	}

	public Date getCreationDateTicket() {
		return this.creationDateTicket;
	}

	public Date setCreationDateTicket(Date date) {
		this.creationDateTicket = date
	}
}
