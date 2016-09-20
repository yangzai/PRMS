package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao on 15/09/16.
 */
public class ScheduleDAOImpl implements ScheduleDAO{
    Connection connection;

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#createValueObject()
     */
    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#getObject(java.lang.String)
     */
    @Override
    public ProgramSlot getObject(String name) throws NotFoundException,
            SQLException {

        ProgramSlot valueObject = createValueObject();
        //valueObject.setName(name);
        //load(valueObject);
        return valueObject;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#load(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
     */
    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException,
            SQLException {

//        if (valueObject.getName() == null) {
//            // System.out.println("Can not select without Primary-Key!");
//            throw new NotFoundException("Can not select without Primary-Key!");
//        }
//
//        String sql = "SELECT * FROM `radio-program` WHERE (`name` = ? ); ";
//        PreparedStatement stmt = null;
//        openConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, valueObject.getName());
//
//            singleQuery(stmt, valueObject);
//
//        } finally {
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#loadAll()
     */
    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
//        openConnection();
//        String sql = "SELECT * FROM `radio-program` ORDER BY `name` ASC; ";
//        List<RadioProgram> searchResults = listQuery(connection
//                .prepareStatement(sql));
//        closeConnection();
//        System.out.println("record size"+searchResults.size());
//        return searchResults;
        return null;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#create(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
     */
    @Override
    public synchronized void create(ProgramSlot valueObject)
            throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
//        openConnection();
//        try {
//            sql = "INSERT INTO `radio-program` (`name`, `desc`, `typicalDuration`) VALUES (?,?,?); ";
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, valueObject.getName());
//            stmt.setString(2, valueObject.getDescription());
//            stmt.setTime(3, valueObject.getTypicalDuration());
//            int rowcount = databaseUpdate(stmt);
//            if (rowcount != 1) {
//                // System.out.println("PrimaryKey Error when updating DB!");
//                throw new SQLException("PrimaryKey Error when updating DB!");
//            }
//
//        } finally {
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }

    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#save(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
     */
    @Override
    public void save(ProgramSlot valueObject) throws NotFoundException,
            SQLException {

        String sql = "UPDATE `radio-program` SET `desc` = ?, `typicalDuration` = ? WHERE (`name` = ? ); ";
        PreparedStatement stmt = null;
//        openConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, valueObject.getDescription());
//            stmt.setTime(2, valueObject.getTypicalDuration());
//
//            stmt.setString(3, valueObject.getName());
//
//            int rowcount = databaseUpdate(stmt);
//            if (rowcount == 0) {
//                // System.out.println("Object could not be saved! (PrimaryKey not found)");
//                throw new NotFoundException(
//                        "Object could not be saved! (PrimaryKey not found)");
//            }
//            if (rowcount > 1) {
//                // System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
//                throw new SQLException(
//                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
//            }
//        } finally {
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#delete(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
     */
    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException,
            SQLException {

//        if (valueObject.getName() == null) {
//            // System.out.println("Can not delete without Primary-Key!");
//            throw new NotFoundException("Can not delete without Primary-Key!");
//        }
//
//        String sql = "DELETE FROM `radio-program` WHERE (`name` = ? ); ";
//        PreparedStatement stmt = null;
//        openConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, valueObject.getName());
//
//            int rowcount = databaseUpdate(stmt);
//            if (rowcount == 0) {
//                // System.out.println("Object could not be deleted (PrimaryKey not found)");
//                throw new NotFoundException(
//                        "Object could not be deleted! (PrimaryKey not found)");
//            }
//            if (rowcount > 1) {
//                // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
//                throw new SQLException(
//                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
//            }
//        } finally {
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#deleteAll(java.sql.Connection)
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

//        String sql = "DELETE FROM `radio-program`";
//        PreparedStatement stmt = null;
//        openConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//            int rowcount = databaseUpdate(stmt);
//            System.out.println(""+rowcount);
//        } finally {
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//
//        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#countAll()
     */
    @Override
    public int countAll() throws SQLException {

//        String sql = "SELECT count(*) FROM `radio-program`";
//        PreparedStatement stmt = null;
//        ResultSet result = null;
//        int allRows = 0;
//        openConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//            result = stmt.executeQuery();
//
//            if (result.next())
//                allRows = result.getInt(1);
//        } finally {
//            if (result != null)
//                result.close();
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
//        return allRows;
        return 0;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#searchMatching(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
     */
    @Override
    public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {

//        @SuppressWarnings("UnusedAssignment")
//        List<RadioProgram> searchResults = new ArrayList<>();
//        openConnection();
//        boolean first = true;
//        StringBuilder sql = new StringBuilder(
//                "SELECT * FROM `radio-program` WHERE 1=1 ");
//
//        if (valueObject.getName() != null) {
//            if (first) {
//                first = false;
//            }
//            sql.append("AND `name` LIKE '").append(valueObject.getName())
//                    .append("%' ");
//        }
//
//        if (valueObject.getDescription() != null) {
//            if (first) {
//                first = false;
//            }
//            sql.append("AND `desc` LIKE '").append(valueObject.getDescription())
//                    .append("%' ");
//        }
//
//        if (valueObject.getTypicalDuration() != null) {
//            if (first) {
//                first = false;
//            }
//            sql.append("AND `typicalDuration` = '")
//                    .append(valueObject.getTypicalDuration()).append("' ");
//        }
//
//        sql.append("ORDER BY `name` ASC ");
//
//        // Prevent accidential full table results.
//        // Use loadAll if all rows must be returned.
//        if (first)
//            searchResults = new ArrayList<>();
//        else
//            searchResults = listQuery(connection.prepareStatement(sql
//                    .toString()));
//        closeConnection();
//        return searchResults;
        return null;
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
    protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;
//        openConnection();
//        try {
//            result = stmt.executeQuery();
//
//            if (result.next()) {
//
//                valueObject.setName(result.getString("name"));
//                valueObject.setDescription(result.getString("desc"));
//                valueObject.setTypicalDuration(result
//                        .getTime("typicalDuration"));
//
//            } else {
//                // System.out.println("RadioProgram Object Not Found!");
//                throw new NotFoundException("RadioProgram Object Not Found!");
//            }
//        } finally {
//            if (result != null)
//                result.close();
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
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
    protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

//        ArrayList<RadioProgram> searchResults = new ArrayList<>();
//        ResultSet result = null;
//        openConnection();
//        try {
//            result = stmt.executeQuery();
//
//            while (result.next()) {
//                RadioProgram temp = createValueObject();
//
//                temp.setName(result.getString("name"));
//                temp.setDescription(result.getString("desc"));
//                temp.setTypicalDuration(result.getTime("typicalDuration"));
//
//                searchResults.add(temp);
//            }
//
//        } finally {
//            if (result != null)
//                result.close();
//            if (stmt != null)
//                stmt.close();
//            closeConnection();
//        }
//
//        return (List<RadioProgram>) searchResults;
        return null;
    }

    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}