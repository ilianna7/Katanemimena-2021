package gr.hua.katanemimena.group40.project2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.katanemimena.group40.project2.entities.Application;
import gr.hua.katanemimena.group40.project2.entities.User;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	UserDAO userDAO;

	@Override
	@Transactional
	public List<Application> getApplications() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Application> query = currentSession.createQuery("from Application", Application.class);

		// execute the query and get the results list
		List<Application> apps = query.getResultList();

		// return the results
		return apps;
	}

	@Override
	@Transactional
	public int saveApplication(Application app) {

		int flag = 1;

		// New factory Session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			User user = session.get(User.class, app.getEmployee_id().getId());
			app.setStatus("Processing");
			app.setAccepted("No");
			user.add(app);
			// save the new user
			session.save(app);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			flag = 0;

		} finally {
			factory.close();
		}

		// If transaction was done successfully, return 1
		return flag;

	}

	@Override
	@Transactional
	public Application getApplicationByID(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Application app = currentSession.get(Application.class, id);
		return app;

	}

	@Override
	@Transactional
	public Application deleteApplication(int id) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		Application app = new Application();
		
		try {
			session.beginTransaction();

			// Get user to be deleted by id
			app = (Application) session.load(Application.class, id);

			// Delete user
			session.delete(app);

			// Commit
			session.flush();
			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			factory.close();
		}

		return app;

	}

	@Override
	@Transactional
	public Application editApplication(Application app, int id) {

		// New factory Session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		Application oldapp = new Application();
		
		try {
			session.beginTransaction();

			oldapp = session.get(Application.class, id);

			oldapp.setEnd_date(app.getEnd_date());
			oldapp.setStart_date(app.getStart_date());
			oldapp.setReason(app.getReason());

			// save the new user
			session.merge(oldapp);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {

		} finally {
			factory.close();
		}

		// If transaction was done successfully, return 1
		return oldapp;

	}

	@Override
	@Transactional
	public List<Application> getApplicationByAccepted(String str) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		List<Application> apps = new ArrayList<Application>();
		try {
			session.beginTransaction();

			String hql = "from Application where accepted like :keyword";
			String keyword = str;

			@SuppressWarnings({ "unchecked" })
			Query<Application> query = session.createQuery(hql);
			query.setParameter("keyword", "%" + keyword + "%");

			apps = query.list();

		} catch (Exception e) {
		} finally {
			factory.close();

		}
		return apps;
	}

	@Override
	@Transactional
	public List<Application> getApplicationsByStatus(String str) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		List<Application> apps = new ArrayList<Application>();
		try {
			session.beginTransaction();

			String hql = "from Application where status like :keyword";
			String keyword = str;

			@SuppressWarnings({ "unchecked" })
			Query<Application> query = session.createQuery(hql);
			query.setParameter("keyword", "%" + keyword + "%");

			apps = query.list();

		} catch (Exception e) {
		} finally {
			factory.close();

		}
		return apps;
	}

	@Override
	@Transactional
	public List<Application> getApplicationsPending() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		List<Application> apps = new ArrayList<Application>();
		try {
			session.beginTransaction();

			String hql = "from Application where status like :keyword and accepted like :str";
			String keyword = "Processing";
			String str = "No";

			@SuppressWarnings({ "unchecked" })
			Query<Application> query = session.createQuery(hql);
			query.setParameter("keyword", "%" + keyword + "%");
			query.setParameter("str", "%" + str + "%");

			apps = query.list();

		} catch (Exception e) {
		} finally {
			factory.close();

		}
		return apps;
	}

	@Override
	@Transactional
	public Application updateStatus(Application app) {

		// New factory Session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Application oldapp = session.get(Application.class, app.getId());

			oldapp = deleteApplication(oldapp.getId());

			// save the new user
			session.save(app);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {

		} finally {
			factory.close();
		}

		// If transaction was done successfully, return 1
		return app;

	}

	@Override
	@Transactional
	public List<Application> getApplicationsByUsername(String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		List<Application> apps = new ArrayList<Application>();
		try {
			session.beginTransaction();

			User user = userDAO.getUserByUsername(username);

			String hql = "from Application where employee_id like :keyword";
			User keyword = user;

			@SuppressWarnings({ "unchecked" })
			Query<Application> query = session.createQuery(hql);
			query.setParameter("keyword", keyword);

			apps = query.list();

		} catch (Exception e) {
		} finally {
			factory.close();

		}
		return apps;

	}


	@Override
	@Transactional
	public List<Application> getApplicationsByReason(String str){
		
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Application.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		List<Application> apps = new ArrayList<Application>();
		try {
			session.beginTransaction();


			String hql = "from Application where reason like :keyword";
			String keyword = str;

			@SuppressWarnings({ "unchecked" })
			Query<Application> query = session.createQuery(hql);
			query.setParameter("keyword", "%" + keyword + "%" );

			apps = query.list();

		} catch (Exception e) {
		} finally {
			factory.close();

		}
		return apps;
		
	}
	
}
