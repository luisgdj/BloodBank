package bloodbank.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import bloodbank.ifaces.UserManager;
import dogclinic.pojos.Role;

public class JPAUserManager implements UserManager {
	
	EntityManager em;

	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("bloodbank-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// Create the needed roles
		if (this.getRoles().isEmpty()) {
			Role owner = new Role("owner");
			Role vet = new Role("vet");
			this.createRole(vet);
			this.createRole(owner);
		}
	}
}
