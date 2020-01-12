package business.system;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Ticket extends Component {

	private String statusTicket;
	private Date creationDateTicket;

	public Ticket(int id, String title, String description, String status) {
		setId(id);
		setName(title);
		setDescription(description);
		setStatusTicket(status);
		setCreationDateTicket(Date.valueOf(LocalDate.now()));
	}

	public String getStatusTicket() {
		return this.statusTicket;
	}

	public void setStatusTicket(String status) {
		this.statusTicket = status;
	}

	public Date getCreationDateTicket() {
		return this.creationDateTicket;
	}

	public void setCreationDateTicket(Date date) {
		this.creationDateTicket = date;
	}
}
