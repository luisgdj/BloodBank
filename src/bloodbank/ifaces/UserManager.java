package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Role;
import bloodbank.pojos.User;

public interface UserManager {

	public void register(User user);
	public void createRole(Role role);
	public void assignRole(User user, Role role);
	
	public Role getRole(String name);
	public List<Role> getRoles();
	
	public User logIn(String name, String password);
	public User changePassword(User user, String newPassword);
}
