package gr.hua.katanemimena.group40.project2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gr.hua.katanemimena.group40.project2.dao.ApplicationDAO;
import gr.hua.katanemimena.group40.project2.dao.UserDAO;
import gr.hua.katanemimena.group40.project2.entities.Application;
import gr.hua.katanemimena.group40.project2.entities.ApplicationForm;
import gr.hua.katanemimena.group40.project2.entities.SearchForm;
import gr.hua.katanemimena.group40.project2.entities.User;

@Controller
public class AppController {

	@Autowired
	ApplicationDAO appDAO;
	
	@Autowired
	UserDAO userDAO;
	

	@RequestMapping(value = "/admin/viewall", method = RequestMethod.GET)
	public String homepage(Model model) {
		model.addAttribute("username", getCurrentUsername());
		//change to get applications by user
		List<Application> apps = appDAO.getApplications();
		model.addAttribute("apps", apps);
		
		User user = userDAO.getUserByUsername(getCurrentUsername());
		model.addAttribute("user", user);
		
	 return "emp_view_apps";
	
	}
	
	@RequestMapping(value = "/viewapps", method = RequestMethod.GET)
	public String viewapps(Model model) {
		model.addAttribute("username", getCurrentUsername());
		//change to get applications by user
		List<Application> apps = appDAO.getApplicationsByUsername(getCurrentUsername());
		model.addAttribute("apps", apps);
		User user = userDAO.getUserByUsername(getCurrentUsername());
		model.addAttribute("user", user);
		
	 return "emp_view_apps";
	
	}
	
	@RequestMapping(value = "/newapp", method = RequestMethod.GET)
	public String newapp(Model model) {
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		//change to get applications by user
		
		User user = userDAO.getUserByUsername(username);
		model.addAttribute("user", user);
		
		Application app = new Application();
		model.addAttribute("newapp", app);
		
		List<String> reasonList = new ArrayList<String>();
		reasonList.add("Anual");
		reasonList.add("Student");
		reasonList.add("Parent");
		reasonList.add("Sick");
		reasonList.add("Maternity");
		reasonList.add("Agrotiki");
		reasonList.add("Special");
		reasonList.add("Strike");
		
		model.addAttribute("reasonList", reasonList);
		
	 return "emp_new_app";
	
	}
	
	@RequestMapping(value = "/newapp", method = RequestMethod.POST)
	public String newapp(@ModelAttribute("newapp") Application app, Map<String, Object> model, RedirectAttributes redirectAttributes)  {

		String username = getCurrentUsername();
		model.put("username", username);
		
		User user = userDAO.getUserByUsername(username);
	
		app.setEmployee_id(user);
		app.setAccepted("No");
		
		int result = appDAO.saveApplication(app);
		
	
		if(result == 1) {
			model.put("app", app);
			model.put("msg", " Created Successfully");

			model.put("user", user);
			return "open_app";
		
		}else {
			redirectAttributes.addFlashAttribute("catch_error", 1);
			return"redirect:/newapp";
		}
		
	}

	
	@RequestMapping(value = {"/editapp", "/admin/editapp"}, method = RequestMethod.POST, params = "EditApp")
	public String editapp(@ModelAttribute("editapp") Application app, Map<String, Object> model,
			@RequestParam String EditApp){
		String username = getCurrentUsername();
		model.put("username", username);
		//change to get applications by user
		
		User user = userDAO.getUserByUsername(username);
		model.put("user", user);
		
		app = appDAO.getApplicationByID(Integer.parseInt(EditApp));
		model.put("app", app);
		
		Application newapp = new Application();
		model.put("editapp", newapp);
		
		List<String> reasonList = new ArrayList<String>();
		reasonList.add("Anual");
		reasonList.add("Student");
		reasonList.add("Parent");
		reasonList.add("Sick");
		reasonList.add("Maternity");
		reasonList.add("Agrotiki");
		reasonList.add("Special");
		reasonList.add("Strike");
		
		model.put("reasonList", reasonList);
		
	 return "edit_app";
	
	}
	
	@RequestMapping(value = {"/openapp", "/admin/openapp"}, method = RequestMethod.POST, params = "OpenApp")
	public String openapp(@ModelAttribute("openapp") Application app, Map<String, Object> model,
			@RequestParam String OpenApp){

		String username = getCurrentUsername();
		User user = userDAO.getUserByUsername(username);
		model.put("user", user);
		
		Application newapp = appDAO.getApplicationByID(	Integer.parseInt(OpenApp));
		
			model.put("app", newapp);
			model.put("user", user);
			return "open_app";
	
	}
	
	@RequestMapping(value = {"/openapp", "/admin/openapp"}, method = RequestMethod.POST, params = "DeleteApp")
	public String deleteapp(@ModelAttribute("openapp") Application app, Map<String, Object> model,
			@RequestParam String DeleteApp, RedirectAttributes redirectAttributes){

		String username = getCurrentUsername();
		model.put("username", username);
		
			Application application = appDAO.deleteApplication(Integer.parseInt(DeleteApp));
		
			return "redirect:/";
	
	}
	
	
	@RequestMapping(value = {"/editapp", "/admin/editapp"}, method = RequestMethod.POST, params = "SaveApp")
	public String saveeditapp(@ModelAttribute("editapp") Application app, Map<String, Object> model,
			@RequestParam String SaveApp){
		
		String username = getCurrentUsername();
		model.put("username", username);
		User user = userDAO.getUserByUsername(username);
		
		Application newapp = appDAO.editApplication(app, Integer.parseInt(SaveApp));
		
			model.put("app", newapp);
			model.put("msg", " Edited Successfully");
			model.put("user", user);
			return "open_app";

	
	}
	
	@RequestMapping(value = {"/editapp", "/admin/editapp"}, method = RequestMethod.POST, params = "AcceptApp")
	public String acceptapp(@ModelAttribute("editapp") Application app2, Map<String, Object> model,
			@RequestParam String AcceptApp){
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		User user = userDAO.getUserByUsername(username);
		Application app = appDAO.getApplicationByID(Integer.parseInt(AcceptApp));
		
		if(user.getAuthority().equals("ROLE_SUPER")) {
			app.setStatus("Accepted");
			
		}else if(user.getAuthority().equals("ROLE_TM")) {
			app.setAccepted("Yes");
		}else {
			if(app.getAccepted().equals("No")) {
				app.setAccepted("Yes");
			}else {
				app.setStatus("Accepted");
			}
		}
		
		
		Application newapp = appDAO.updateStatus(app);
		
			model.put("app", newapp);
			model.put("msg", " Accepted Successfully");
			model.put("user", user);
			return "open_app";

	
	}
	
	@RequestMapping(value = {"/editapp", "/admin/editapp"}, method = RequestMethod.POST, params = "RejectApp")
	public String rejectapp(@ModelAttribute("editapp") Application app2, Map<String, Object> model,
			@RequestParam String RejectApp){
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		User user = userDAO.getUserByUsername(username);
		Application app = appDAO.getApplicationByID(Integer.parseInt(RejectApp));
		
		if(user.getAuthority().equals("ROLE_SUPER")) {
			app.setStatus("Rejected");
			
		}else if(user.getAuthority().equals("ROLE_TM")) {
			app.setAccepted("Yes");
		}else {
			if(app.getAccepted().equals("No")) {
				app.setAccepted("Yes");
			}else {
				app.setStatus("Rejected");
			}
		}
		
		
		Application newapp = appDAO.updateStatus(app);
		
			model.put("app", newapp);
			model.put("msg", " Rejected Successfully");
			model.put("user", user);
			return "open_app";

	
	}
	
	
	
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public String pending(Model model) {
		String username = getCurrentUsername();
		
		model.addAttribute("username", username);
		//change to get applications by user
		List<Application> apps;
		User user = userDAO.getUserByUsername(username);
		if(user.getAuthority().equals("ROLE_SUPER")) {

			apps = appDAO.getApplicationsByStatus("Processing");
			
		}else if(user.getAuthority().equals("ROLE_TM")) { 
			apps = appDAO.getApplicationByAccepted("No"); 
		}else {
			apps = appDAO.getApplicationsPending();
			
		}
		
		
		model.addAttribute("apps", apps);
		model.addAttribute("user", user);
		
	 return "emp_view_apps";
	}
	
	@RequestMapping(value = "/admin/adminnewapp", method = RequestMethod.GET)
	public String adminnewapp(Model model) {
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		//change to get applications by user
		
		List<User> users = userDAO.getUsers();
		model.addAttribute("employeeList", users);
		
		ApplicationForm app = new ApplicationForm();
		model.addAttribute("adminnewapp", app);
		
		List<String> reasonList = new ArrayList<String>();
		reasonList.add("Anual");
		reasonList.add("Student");
		reasonList.add("Parent");
		reasonList.add("Sick");
		reasonList.add("Maternity");
		reasonList.add("Agrotiki");
		reasonList.add("Special");
		reasonList.add("Strike");
		
		model.addAttribute("reasonList", reasonList);
		
	 return "admin_new_app";
	
	}
	
	@RequestMapping(value = "/admin/adminnewapp", method = RequestMethod.POST)
	public String saveadminnewapp(@ModelAttribute("adminnewapp") ApplicationForm appForm, Map<String, Object> model, RedirectAttributes redirectAttributes)  {

		String username = getCurrentUsername();
		model.put("username", username);
		
		User employee = userDAO.getUserByUsername(appForm.getEmployee());
		
		Application app = appForm.getApplication();
		app.setEmployee_id(employee);
		
		int result = appDAO.saveApplication(app);
		
	
		if(result == 1) {
			model.put("app", app);
			model.put("msg", " Created Successfully");

			return "open_app";
		
		}else {
			redirectAttributes.addFlashAttribute("catch_error", 1);
			return"redirect:/admin/newapp";
		}
		
	}
	
	@RequestMapping(value = "/admin/searchapps", method = RequestMethod.GET)
	public String search(Model model) {
		
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		
		List<String> sByList = new ArrayList<String>();
		sByList.add("id");
		sByList.add("username");
		sByList.add("reason");
			

		model.addAttribute("sByList", sByList);
		model.addAttribute("type", "Applications");
		
		SearchForm myForm = new SearchForm();
		model.addAttribute("searchapps", myForm);
		
		return "search_apps";
	}
	

	@RequestMapping(value = "/admin/searchapps", method = RequestMethod.POST)
	public String searchpost(@ModelAttribute("searchapps") SearchForm searchForm, Map<String, Object> model) {
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		List<Application> appsList = new ArrayList<Application>();
		
		if(searchForm.getsBy().equals("username")) {
			List<User> users = userDAO.getUsersByUsername(searchForm.getsKeyword());
			
			for(int i=0;i<users.size();i++) {
				appsList = appDAO.getApplicationsByUsername(users.get(0).getUsername());
			}
			
		}else if(searchForm.getsBy().equals("id")) {
			appsList.add(appDAO.getApplicationByID(Integer.parseInt(searchForm.getsKeyword())));
			
		}else {
			appsList = appDAO.getApplicationsByReason(searchForm.getsKeyword());
			
		}
		
		
		model.put("apps", appsList);
		User user = userDAO.getUserByUsername(getCurrentUsername());
		model.put("user", user);
		
		return "emp_view_apps";
		
	}
	
	@RequestMapping(value = "/searchmyapps", method = RequestMethod.GET)
	public String search2(Model model) {
		
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		
		List<String> sByList = new ArrayList<String>();
		sByList.add("id");
		sByList.add("username");
		sByList.add("reason");
			

		model.addAttribute("sByList", sByList);
		model.addAttribute("type", "Applications");
		
		SearchForm myForm = new SearchForm();
		model.addAttribute("searchmyapps", myForm);
		
		return "search_myapps";
	}
	

	@RequestMapping(value = "/searchmyapps", method = RequestMethod.POST)
	public String searchpost2(@ModelAttribute("searchmyapps") SearchForm searchForm, Map<String, Object> model) {
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		User user = userDAO.getUserByUsername(username);
		
		List<Application> appsList = new ArrayList<Application>();
		
		if(searchForm.getsBy().equals("username")) {
			List<User> users = userDAO.getUsersByUsername(searchForm.getsKeyword());
			
			for(int i=0;i<users.size();i++) {
				appsList = appDAO.getApplicationsByUsername(users.get(0).getUsername());
			}
			
		}else if(searchForm.getsBy().equals("id")) {
			appsList.add(appDAO.getApplicationByID(Integer.parseInt(searchForm.getsKeyword())));
			
		}else {
			appsList = appDAO.getApplicationsByReason(searchForm.getsKeyword());
			
		}
		
		List<Application> apps = new ArrayList<Application>();
		
		for(int i=0;i<appsList.size();i++) {
			
			if(user.getAuthority().equals("ROLE_SUPER")) {
				if(appsList.get(i).getStatus().equals("Processing")) {
					apps.add(appsList.get(i));
				}
			}else if(user.getAuthority().equals("ROLE_TM")) {
				if(appsList.get(i).getStatus().equals("Accepted") && appsList.get(i).getAccepted().equals("No")) {
					apps.add(appsList.get(i));
				}
			}else {
				apps = appsList;
			}
		}
		
		model.put("apps", apps);
		model.put("user", user);
		
		return "emp_view_apps";
		
	}
	
	private String getCurrentUsername() {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		return username;
	}

}
