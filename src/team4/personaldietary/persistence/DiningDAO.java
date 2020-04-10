package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Serving;

import java.sql.SQLException;

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
    public ObservableList<Dining> findAllDining() throws SQLException ;

    // Update
    public int updateDining(Dining dining) throws SQLException ;
    // Delete
//    public int deleteDining(int diningId) throws SQLException;
    //endregion

    public int createServing(Serving serving) throws SQLException;
    /*
    //region ServingDAO
    // Create

    // Read
    public Serving findServingById(int servingId) throws SQLException;
    public int findServingIdByDiningId(int diningId) throws SQLException;
    // Delete
    public int deleteServing(int servingId) throws SQLException;
    //endregion
     */

}
