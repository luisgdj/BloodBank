package bloodbank.jpa;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bloodbank.ifaces.UserManager;
import bloodbank.pojos.Role;
import bloodbank.pojos.User;

public class JPAUserManager implements UserManager {

	EntityManager em;

	public JPAUserManager() {

		em = Persistence.createEntityManagerFactory("bloodbank-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the needed roles
		if (this.getRoles().isEmpty()) {
			Role manager = new Role("manager");
			Role nurse = new Role("nurse");
			this.createRole(manager);
			this.createRole(nurse);

			User user = new User("manager", "default0", "manager@bloodBank.com");
			register(user);
			Role role = getRole("manager");
			assignRole(user, role);
		}
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
	}

	public void close() {
		em.close();
	}

	@Override
	public void register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	@Override
	public void assignRole(User user, Role role) {
		em.getTransaction().begin();
		user.setRole(role);
		role.addUser(user);
		em.getTransaction().commit();
	}
	
	@Override
	public User changePassword(User user, String newPassword) {
		
		try {
			Query sql = em.createNativeQuery("UPDATE users SET password = ? WHERE username = ? AND password = ?", User.class);
			sql.setParameter(1, newPassword);
			sql.setParameter(2, user.getUsername());
			sql.setParameter(3, user.getPassword());
			user = (User) sql.getSingleResult();
			return user;
		}catch(NoResultException e) {
			return null;
		}
		//sql.executeUpdate();
	}

	@Override
	public User logIn(String username, String password) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, password);
			User user = (User) q.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
}
