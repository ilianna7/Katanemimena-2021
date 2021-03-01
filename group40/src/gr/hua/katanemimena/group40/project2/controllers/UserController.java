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

import gr.hua.katanemimena.group40.project2.dao.UserDAO;
import gr.hua.katanemimena.group40.project2.entities.Application;
import gr.hua.katanemimena.group40.project2.entities.SearchForm;
import gr.hua.katanemimena.group40.project2.entities.User;

@Controller
public class UserController {

	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/admin/newuser", method = RequestMethod.GET)
	public String newuser(Model model) {
		model.addAttribute("username", getCurrentUsername());
		
		User user = new User();
		model.addAttribute("newuser", user);
		
		
		List<String> roleList = new ArrayList<>();
		roleList.add("ROLE_ADMIN");
		roleList.add("ROLE_SUPER");
		roleList.add("ROLE_TM");
		roleList.add("ROLE_EMPLOYEE");
		model.addAttribute("roleList", roleList);

		List<Integer> enabledList = new ArrayList<>();
		enabledList.add(1);
		enabledList.add(0);
		model.addAttribute("enabledList", enabledList);
		
		model.addAttribute("error", 0);		
		
	 return "admin_new_user";
	
	}
	
	@RequestMapping(value = "/admin/newuser", method = RequestMethod.POST)
	public String newuserpost(@ModelAttribute("newuser") User newuser, Map<String, Object> model, RedirectAttributes redirectAttributes) {
		String username = getCurrentUsername();
		model.put("username", username);
		
		User user = userDAO.getUserByUsername(username);
		
		String temp = String.valueOf(newuser.getAfm());
		
		if(temp.length() !=9) {
			redirectAttributes.addFlashAttribute("catch_error", 2);
			return"redirect:/admin/newuser";
			
		}
		
		int result = userDAO.newUser(newuser);
		
	
		if(result == 1) {
			model.put("user", newuser);
			model.put("msg", " Created Successfully");

			return "open_user";
		
		}else {
			redirectAttributes.addFlashAttribute("catch_error", 1);
			return"redirect:/admin/newuser";
		}
	
	}
	
	
	@RequestMapping(value = "/admin/printusers", method = RequestMethod.GET)
	public String printusers(Model model) {
		
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		
		List<User> usersList = userDAO.getUsers();
		model.addAttribute("usersList", usersList);
		
		
		return "admin_view_users";
	}
	
	@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "DeleteUser") 
	public String deleteuser(@ModelAttribute("edituser") User user, Map<String, Object> model,
			@RequestParam String DeleteUser){
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		userDAO.deleteUserByID(Integer.parseInt(DeleteUser));
		
		
		List<User> usersList = userDAO.getUsers();
		model.put("usersList", usersList);
		model.put("deletedvar", 1);
		
		return "admin_view_users";

	}
	
	@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "EditUser") 
	public String edituser(@ModelAttribute("edituser") User user, Map<String, Object> model,
			@RequestParam String EditUser){
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		List<String> roleList = new ArrayList<>();
		roleList.add("ROLE_ADMIN");
		roleList.add("ROLE_SUPER");
		roleList.add("ROLE_TM");
		roleList.add("ROLE_EMPLOYEE");
		model.put("roleList", roleList);

		List<Integer> enabledList = new ArrayList<>();
		enabledList.add(1);
		enabledList.add(0);
		model.put("enabledList", enabledList);
		
		User myuser = userDAO.getUserByUsername(EditUser);
		
		model.put("user", myuser);
		
		return "admin_edit_user";

	}
	
	
	
	@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "SaveUser")
	public String saveEdits(@ModelAttribute("edituser") User user, Map<String, Object> model,
			@RequestParam String SaveUser) {

		model.put("username", getCurrentUsername());

		User newuser = userDAO.updateUser(user, SaveUser);

		//Display result
		model.put("msg", "Updated Successfully");
		model.put("user", newuser);

		return "open_user";

	}
	
	@RequestMapping(value = "/admin/search", method = RequestMethod.GET)
	public String search(Model model) {
		
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		
		List<String> sByList = new ArrayList<String>();
		sByList.add("id");
		sByList.add("username");
		sByList.add("Name");
		sByList.add("afm");
		sByList.add("email");
			

		model.addAttribute("sByList", sByList);
		model.addAttribute("type", "Users");
		
		SearchForm myForm = new SearchForm();
		model.addAttribute("searchform", myForm);
		
		return "search_users";
	}
	
	@RequestMapping(value = "/admin/search", method = RequestMethod.POST)
	public String searchpost(@ModelAttribute("searchform") SearchForm searchForm, Map<String, Object> model) {
		
		String username = getCurrentUsername();
		model.put("username", username);
		
		List<User> usersList = new ArrayList<User>();
		
		if(searchForm.getsBy().equals("Name")) {
			usersList = userDAO.getUsersByName(searchForm);
			
		}else if(searchForm.getsBy().equals("id") || searchForm.getsBy().equals("afm")) {
			usersList = userDAO.getUsersByIntKeyword(searchForm);
			
		}else {
			usersList = userDAO.getUsersByKeyword(searchForm);
			
		}
		
		
		model.put("usersList", usersList);
		
		return "admin_view_users";
		
	}
	
	

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editprofile(Model model) {
		
		String username = getCurrentUsername();
		model.addAttribute("username", username);
		
		User user = userDAO.getUserByUsername(username);

		User newuser = new User();
		model.addAttribute("editprofile", newuser);
		model.addAttribute("user", user);
		
		return "edit_profile";
	}
	
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.POST, params = "SaveProfile")
	public String editprofilepost(@ModelAttribute("editprofile") User user, Map<String, Object> model,
			@RequestParam String SaveProfile) {

		model.put("username", getCurrentUsername());

		User newuser = userDAO.updateUserProfile(user, SaveProfile);

		//Display result
		model.put("msg", "Updated Successfully");
		model.put("user", newuser);

		return "open_user";

	}
	
	
	
private String getCurrentUsername() {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		return username;
	}

	
	
	
}
