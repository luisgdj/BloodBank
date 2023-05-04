package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Role;
import bloodbank.pojos.User;

public interface UserManager {

	public void register(User user);
	public void createRole(Role role);
	public Role getRole(String name);
	public List<Role> getRoles();
	public void assignRole(User user, Role role);
	// If user doesn't exist return null
	public User logIn(String name, String password);
}
