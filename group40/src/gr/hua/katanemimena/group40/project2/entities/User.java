package gr.hua.katanemimena.group40.project2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table (name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username", unique = true)
    private String username;
	
	@Column(name = "password")
    private String password;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "afm", unique = true)
	private int afm;

	@Column(name = "authority")
    private String authority;
	
	@Column(name = "enabled")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;

	@OneToMany(mappedBy="employee_id",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                                   CascadeType.DETACH, CascadeType.REFRESH})
	private List<Application> applications;
	
	public User() {}
	
	
	public User(String username, String password, String firstName, String lastName, String email, int afm,
			String authority, boolean enabled) {
		super();
		this.username = username;
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.afm = afm;
		this.authority = authority;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Application> getApplications() {
		return applications;
	}


	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public void add(Application app) {
	     if(applications == null) {
	    	 applications = new ArrayList<>();
	     }
	     applications.add(app);
	     app.setEmployee_id(this);
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", afm=" + afm + ", authority=" + authority
				+ ", enabled=" + enabled + " ]";
	}
	
	
	
}
