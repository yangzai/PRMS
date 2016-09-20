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
