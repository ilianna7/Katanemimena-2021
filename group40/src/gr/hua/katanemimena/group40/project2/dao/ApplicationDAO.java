package gr.hua.katanemimena.group40.project2.dao;

import java.util.List;

import gr.hua.katanemimena.group40.project2.entities.Application;

public interface ApplicationDAO {

	public List<Application> getApplications();
	
	public int saveApplication(Application app);
	
	public Application getApplicationByID(int id);
	
	public Application editApplication(Application app, int id);
	
	public Application deleteApplication(int id);
	
	public List<Application> getApplicationByAccepted(String str); 
	public List<Application> getApplicationsByStatus(String str);
	public List<Application> getApplicationsPending();
	
	public List<Application> getApplicationsByUsername(String username);
	
	public Application updateStatus(Application app);
	
	public List<Application> getApplicationsByReason(String str);
}
