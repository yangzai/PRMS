package sg.edu.nus.iss.phoenix.schedule.dao;

import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Created by yao on 15/09/16.
 */
public interface ScheduleDAO {
    public abstract ProgramSlot createValueObject();

    /**
     * getObject-method. This will create and load valueObject contents from
     * database using given Primary-Key as identifier. This method is just a
     * convenience method for the real load-method which accepts the valueObject
     * as a parameter. Returned valueObject will be created using the
     * createValueObject() method.
     * @param duration
     * @param dateOfProgram
     * @return
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    public abstract ProgramSlot getObject(Time duration, Date dateOfProgram)
            throws NotFoundException, SQLException;

    public abstract void load(ProgramSlot valueObject)
            throws NotFoundException, SQLException;

    public abstract List<ProgramSlot> loadAll() throws SQLException;

    public abstract void create(ProgramSlot valueObject) throws SQLException;

    public abstract void save(ProgramSlot valueObject)
            throws NotFoundException, SQLException;

    public abstract void delete(ProgramSlot valueObject)
            throws NotFoundException, SQLException;

    public abstract void deleteAll(Connection conn) throws SQLException;

    public abstract int countAll() throws SQLException;

    public abstract List<ProgramSlot> searchMatching(ProgramSlot valueObject)
            throws SQLException;
}
