package team4.personaldietary.persistence;

import team4.personaldietary.bean.Dining;

import java.sql.SQLException;
import java.util.List;

/**
 * The <tt>DiningDAO</tt> interface
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
 */
public interface DiningDAO {

    //region DiningDAO
    // Create
    public int createDining(Dining dining) throws SQLException;

    // Read
    public Dining findDiningById(int diningId) throws SQLException;
    public Dining findDiningByName(String diningName) throws SQLException;
    public List<Dining> findAllDining() throws SQLException ;

    // Update
    public int updateDining(Dining dining) throws SQLException ;

    // Delete
    public int deleteDining(int diningId) throws SQLException;


}
