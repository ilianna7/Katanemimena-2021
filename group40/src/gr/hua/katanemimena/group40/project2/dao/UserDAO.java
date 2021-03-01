package gr.hua.katanemimena.group40.project2.dao;

import java.util.List;

import gr.hua.katanemimena.group40.project2.entities.SearchForm;
import gr.hua.katanemimena.group40.project2.entities.User;

public interface UserDAO {
	public List<User> getUsers();
	

	public User getUserByUsername(String username);
	
	public List<User> getUsersByUsername(String username);
	
	public int newUser(User newuser);
	
	public void deleteUserByID(int id);
	
	public User updateUser(User user, String username);
	
	public List<User> getUsersByKeyword(SearchForm searchForm);
	
	public List<User> getUsersByName(SearchForm searchForm);
	
	public List<User> getUsersByIntKeyword(SearchForm searchForm);
	
	public User updateUserProfile(User user, String username);
	
}


