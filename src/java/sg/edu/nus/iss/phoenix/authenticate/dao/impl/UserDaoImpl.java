package sg.edu.nus.iss.phoenix.authenticate.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 * User Data Access Object (DAO). This class contains all database handling that
 * is needed to permanently store and retrieve User object instances.
 */
public class UserDaoImpl implements UserDao {

	private static final String DELIMITER = ":";
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	Connection connection;


	public UserDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		connection = openConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#createValueObject()
	 */
	@Override
	public User createValueObject() {
		return new User();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#getObject(java.sql
	 * .Connection, int)
	 */
	@Override
	public User getObject(String id) throws NotFoundException, SQLException {

		User valueObject = createValueObject();
		valueObject.setId(id);
		load(valueObject);
		return valueObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#load(java.sql.Connection
	 * , sg.edu.nus.iss.phoenix.authenticate.entity.User)
	 */
	@Override
	public void load(User valueObject) throws NotFoundException, SQLException {

		String sql = "SELECT * FROM user WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getId());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#loadAll(java.sql
	 * .Connection)
	 */
	@Override
	public List<User> loadAll() throws SQLException {

		String sql = "SELECT * FROM user ORDER BY id ASC ";
		List<User> searchResults = listQuery(this.connection
				.prepareStatement(sql));

		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#create(java.sql.
	 * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
	 */
	@Override
	public synchronized void create(User valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		try {
			sql = "INSERT INTO user ( id, password, name, "
					+ "role) VALUES (?, ?, ?, ?) ";
			stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, valueObject.getId());
			stmt.setString(2, valueObject.getPassword());
			stmt.setString(3, valueObject.getName());
			stmt.setString(4, valueObject.getRoles().get(0).getRole());

			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#save(java.sql.Connection
	 * , sg.edu.nus.iss.phoenix.authenticate.entity.User)
	 */
	@Override
	public void save(User valueObject) throws NotFoundException, SQLException {

		String sql = "UPDATE user SET password = ?, name = ?, role = ? WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getPassword());
			stmt.setString(2, valueObject.getName());
			stmt.setString(3, valueObject.getRoles().get(0).getRole());

			stmt.setString(4, valueObject.getId());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#delete(java.sql.
	 * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
	 */
	@Override
	public void delete(User valueObject) throws NotFoundException, SQLException {

		String sql = "DELETE FROM user WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getId());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#deleteAll(java.sql
	 * .Connection)
	 */
	@Override
	public void deleteAll() throws SQLException {

		String sql = "DELETE FROM user";
		PreparedStatement stmt = null;

		try {
			stmt = this.connection.prepareStatement(sql);
			int rowcount = databaseUpdate(stmt);
			System.out.println("Deleted rows :" + rowcount);
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#countAll(java.sql
	 * .Connection)
	 */
	@Override
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM user";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int allRows = 0;

		try {
			stmt = this.connection.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next())
				allRows = result.getInt(1);
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
		return allRows;
	}

	@Override
	public User searchMatching(String uid) throws SQLException {
		try {
			return (getObject(uid));
		} catch (NotFoundException ex) {
			logger.log(Level.WARNING, "Fail to find user: {0}", uid);
		}
		return (null);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#searchMatching(java
	 * .sql.Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
	 */
	@Override
	public List<User> searchMatching(User valueObject) throws SQLException {

		List<User> searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE 1=1 ");

		if (valueObject.getId() != "") {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getPassword() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND password LIKE '").append(valueObject.getPassword())
					.append("%' ");
		}

		if (valueObject.getName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND name LIKE '").append(valueObject.getName())
					.append("%' ");
		}

		if (valueObject.getRoles().get(0).getRole() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND role LIKE '")
					.append(valueObject.getRoles().get(0).getRole())
					.append("%' ");
		}

		sql.append("ORDER BY id ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList<User>();
		else
			searchResults = listQuery(this.connection.prepareStatement(sql
					.toString()));

		return searchResults;
	}

	/**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
	 */
	protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return only one row. The
	 * resultset will be converted to valueObject. If no rows were found,
	 * NotFoundException will be thrown.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 * @param valueObject
	 *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
	 */
	protected void singleQuery(PreparedStatement stmt, User valueObject)
			throws NotFoundException, SQLException {

		try (ResultSet result = stmt.executeQuery()) {

			if (result.next()) {

				valueObject.setId(result.getString("id"));
				valueObject.setPassword(result.getString("password"));
				valueObject.setName(result.getString("name"));
				valueObject.setRoles(createRoles(result.getString("role")));
				//Role e = new Role(result.getString("role"));
				//ArrayList<Role> roles = new ArrayList<Role>();
				//roles.add(e);
				//valueObject.setRoles(roles);

			} else {
				// System.out.println("User Object Not Found!");
				throw new NotFoundException("User Object Not Found!");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
	 */
	protected List<User> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<User> searchResults = new ArrayList<>();
		try (ResultSet result = stmt.executeQuery()) {

			while (result.next()) {
				User temp = createValueObject();

				temp.setId(result.getString("id"));
				temp.setPassword(result.getString("password"));
				temp.setName(result.getString("name"));
				temp.setRoles(createRoles(result.getString("role")));
				//Role e = new Role(result.getString("role"));
				//ArrayList<Role> roles = new ArrayList<Role>();
				//roles.add(e);
				//temp.setRoles(roles);

				searchResults.add(temp);
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

		return (List<User>) searchResults;
	}

	private ArrayList<Role> createRoles(final String roles) {
		ArrayList<Role> roleList = new ArrayList<>();
		String[] _r = roles.trim().split(DELIMITER);
		for (String r: _r)
			roleList.add(new Role(r.trim()));
		return (roleList);
	}

	private Connection openConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/phoenix", "phoenix",
					"password");
		} catch (SQLException e) {
		}
		return conn;
	}
}
