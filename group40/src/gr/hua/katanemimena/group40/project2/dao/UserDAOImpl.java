package gr.hua.katanemimena.group40.project2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.katanemimena.group40.project2.entities.Application;
import gr.hua.katanemimena.group40.project2.entities.SearchForm;
import gr.hua.katanemimena.group40.project2.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ApplicationDAO appDAO;
	
	
	@Override
	@Transactional
	public List<User> getUsers() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<User> query = currentSession.createQuery("from User", User.class);

		// execute the query and get the results list
		List<User> users = query.getResultList();

		// return the results
		return users;
	}
	
	@Override
	@Transactional
	public User getUserByUsername(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "from User where username like :keyword";
		String keyword = username;

		@SuppressWarnings({ "unchecked" })
		Query<User> query = currentSession.createQuery(hql);
		query.setParameter("keyword",  keyword );

		List<User> user = query.list();
		return user.get(0);
		
		
	}
	
	@Override
	@Transactional
	public List<User> getUsersByUsername(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "from User where username like :keyword";
		String keyword = username;

		@SuppressWarnings({ "unchecked" })
		Query<User> query = currentSession.createQuery(hql);
		query.setParameter("keyword",  "%"+keyword +"%");

		List<User> users = query.list();
		
		return users;
	}
	

	
	
	@Override
	@Transactional
	public int newUser(User newuser) {
		
		
int flag =1;
		
		// New factory Session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
		
			// save the new user
			session.save(newuser);

			// commit transaction
			session.getTransaction().commit();
			
			

		}catch(Exception e){
			flag=0;
			
		}finally {
			factory.close();
		}
			

		//If transaction was done successfully, return 1
			return flag;			
		
		
	}
	

	@Override
	@Transactional
	public void deleteUserByID(int id) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Application.class).buildSessionFactory();

 		// create session
 		Session session = factory.getCurrentSession();

    try {
		session.beginTransaction();

		//Get user to be deleted by id
		User user = (User) session.load(User.class, id);

		Application temp = new Application();
		
		List<Application> apps = appDAO.getApplicationsByUsername(user.getUsername());
		for(int i=0;i<apps.size();i++) {
			
			temp = appDAO.deleteApplication(apps.get(i).getId());
			
		}
			
		
		//Delete user
		session.delete(user);

		//Commit 
		session.flush();
		session.getTransaction().commit();

		}catch(Exception e){
		}finally {
			factory.close();
		}
		
	}
	

	@Override
	@Transactional
	public User updateUser(User user, String username) {
		
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Application.class).buildSessionFactory();

		
Session session = factory.getCurrentSession();
		
		User newuser = new User();

		try {
		session.beginTransaction();
		//Get the selected user before edits 
		User olduser = getUserByUsername(username);
		
		//Add user ID and password from original entity
		user.setId(olduser.getId());
		user.setPassword(olduser.getPassword());		
		// save the new user
		newuser = (User) session.merge(user);

		// commit transaction
		session.getTransaction().commit();

		}catch(Exception e){
			
		}finally {
			factory.close();
		}
		
		return newuser;
	}
	
	@Override
	@Transactional
	public List<User> getUsersByKeyword(SearchForm searchForm){
		Session currentSession = sessionFactory.getCurrentSession();

		
		String hql = "from User where "+searchForm.getsBy()+" like :keyword";
		String keyword = searchForm.getsKeyword();

		
		@SuppressWarnings({ "unchecked" })
		Query<User> query = currentSession.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");

		
		List<User> usersList = query.list();
		

		// return the results
		return usersList;
		
	}
	
	@Override
	@Transactional
	public List<User> getUsersByName(SearchForm searchForm){
		
Session currentSession = sessionFactory.getCurrentSession();

		
		String hql = "from User where first_name like :keyword or last_name like :keyword";
		String keyword = searchForm.getsKeyword();

		
		@SuppressWarnings({ "unchecked" })
		Query<User> query = currentSession.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");

		
		List<User> usersList = query.list();
		

		// return the results
		return usersList;
		
		
		
	}


	@Override
	@Transactional
	public List<User> getUsersByIntKeyword(SearchForm searchForm){
		
Session currentSession = sessionFactory.getCurrentSession();

		
		String hql = "from User where "+searchForm.getsBy()+" like :keyword";
		int keyword = Integer.parseInt(searchForm.getsKeyword());

		
		@SuppressWarnings({ "unchecked" })
		Query<User> query = currentSession.createQuery(hql);
		query.setParameter("keyword", keyword);

		
		List<User> usersList = query.list();
		

		// return the results
		return usersList;
		
		
	}
	
	

	@Override
	@Transactional
	public User updateUserProfile(User user, String username) {
		
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Application.class).buildSessionFactory();

		
Session session = factory.getCurrentSession();
		
		User newuser = new User();

		try {
		session.beginTransaction();
		//Get the selected user before edits 
		User olduser = getUserByUsername(username);
		
		//Add user ID and password from original entity
		user.setId(olduser.getId());
		user.setAuthority(olduser.getAuthority());		
		user.setEnabled(olduser.isEnabled());
		// save the new user
		newuser = (User) session.merge(user);

		// commit transaction
		session.getTransaction().commit();

		}catch(Exception e){
			
		}finally {
			factory.close();
		}
		
		return newuser;
	}
	
}
