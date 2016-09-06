package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 * NotFoundException exception. This exception will be thrown from Dao object if
 * load, update or delete for one object fails to find the correct row.
 **/

public class NotFoundException extends Exception {
	/**
	 * For eclipse based unique identity
	 */
	private static final long serialVersionUID = -8937329631346507674L;

	/**
	 * Constructor for NotFoundException. The input message is returned in
	 * toString() message.
	 */
	public NotFoundException(String msg) {
		super(msg);
	}

}
