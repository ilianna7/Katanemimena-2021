package gr.hua.katanemimena.group40.project2.entities;

import javax.persistence.Column;
import javax.validation.Valid;

public class ApplicationForm {

	@Column
	private String employee;
	
	@Valid
	private Application application;

	public ApplicationForm() {}
	
	public ApplicationForm(String employee, Application application) {
		super();
		this.employee = employee;
		this.application = application;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	
}
