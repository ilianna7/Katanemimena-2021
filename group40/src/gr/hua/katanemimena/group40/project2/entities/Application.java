package gr.hua.katanemimena.group40.project2.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "application")
public class Application {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

	@Column(name = "start_date", unique = true)
    private String start_date;

	@Column(name = "end_date", unique = true)
    private String end_date;

	@Column(name = "reason", unique = true)
    private String reason;

	@Column(name = "status", unique = true)
    private String status;
	
	@Column(name = "accepted", unique = true)
    private String accepted;
	
	
	 @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
             CascadeType.DETACH, CascadeType.REFRESH})
		@JoinColumn(name="employee_id")
		private User employee_id;

	 public Application() {}
	 
	

	public Application(String start_date, String end_date, String reason, String status, String accepted,
			User employee_id) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.reason = reason;
		this.status = status;
		this.accepted = accepted;
		this.employee_id = employee_id;
	}



	public String getAccepted() {
		return accepted;
	}



	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(User employee_id) {
		this.employee_id = employee_id;
	}



	@Override
	public String toString() {
		return "Application [id=" + id + ", start_date=" + start_date + ", end_date=" + end_date + ", reason=" + reason
				+ ", status=" + status + ", accepted=" + accepted + ", employee_id=" + employee_id + "]";
	}

	
}


