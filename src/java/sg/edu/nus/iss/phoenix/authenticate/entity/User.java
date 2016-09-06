package sg.edu.nus.iss.phoenix.authenticate.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User Value Object. This class is value object representing database table
 * user This class is intented to be used together with associated Dao object.
 */
public class User implements Cloneable, Serializable {

	/**
	 * For eclipse based unique identity
	 */
	private static final long serialVersionUID = -3737184031423373198L;
	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */
	private String id;
	private String password;
	private String name;
	private ArrayList<Role> roles = new ArrayList<Role>();

	/**
	 * Constructors. The first one takes no arguments and provides the most
	 * simple way to create object instance. The another one takes one argument,
	 * which is the primary key of the corresponding table.
	 */

	public User() {

	}

	public User(String idIn) {

		this.id = idIn;

	}

	/**
	 * Get- and Set-methods for persistent variables. The default behaviour does
	 * not make any checks against malformed data, so these might require some
	 * manual additions.
	 */

	public String getId() {
		return this.id;
	}

	public void setId(String idIn) {
		this.id = idIn;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String passwordIn) {
		this.password = passwordIn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String nameIn) {
		this.name = nameIn;
	}

	public ArrayList<Role> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	/**
	 * setAll allows to set all persistent variables in one method call. This is
	 * useful, when all data is available and it is needed to set the initial
	 * state of this object. Note that this method will directly modify instance
	 * variales, without going trough the individual set-methods.
	 */

	public void setAll(String idIn, String passwordIn, String nameIn,
			String roleIn) {
		this.id = idIn;
		this.password = passwordIn;
		this.name = nameIn;
		Role e = new Role(roleIn);
		this.roles.add(e);
	}

	/**
	 * hasEqualMapping-method will compare two User instances and return true if
	 * they contain same values in all persistent instance variables. If
	 * hasEqualMapping returns true, it does not mean the objects are the same
	 * instance. However it does mean that in that moment, they are mapped to
	 * the same row in database.
	 */
	public boolean hasEqualMapping(User valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (this.password == null) {
			if (valueObject.getPassword() != null)
				return (false);
		} else if (!this.password.equals(valueObject.getPassword())) {
			return (false);
		}
		if (this.name == null) {
			if (valueObject.getName() != null)
				return (false);
		} else if (!this.name.equals(valueObject.getName())) {
			return (false);
		}
		if (this.roles.get(0).getRole() != null) {
			if (valueObject.roles.get(0).getRole() != null)
				return (false);
		} else if (!this.roles.get(0).equals(valueObject.roles.get(0).getRole())) {
			return (false);
		}

		return true;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in console logs.
	 */
	public String toString() {
		StringBuffer out = new StringBuffer("toString: ");
		out.append("\nclass User, mapping to table user\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("password = " + this.password + "\n");
		out.append("name = " + this.name + "\n");
		out.append("role = " + this.roles.get(0).getRole() + "\n");
		return out.toString();
	}

	/**
	 * Clone will return identical deep copy of this valueObject. Note, that
	 * this method is different than the clone() which is defined in
	 * java.lang.Object. Here, the returned cloned object will also have all its
	 * attributes cloned.
	 */
	public Object clone() {
		User cloned = new User();
		if (this.password != null)
			cloned.setPassword(new String(this.password));
		if (this.name != null)
			cloned.setName(new String(this.name));
		if (this.roles.get(0) != null)
			cloned.roles.add(new Role(this.roles.get(0).getRole()));
		return cloned;
	}

}
