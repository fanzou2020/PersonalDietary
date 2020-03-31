package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.Retailer;
import team4.personaldietary.bean.Type;

import java.sql.SQLException;

public interface RetailerDAO {

    // Create
    public int createRetailer(Retailer retailer) throws SQLException;

    // Read
    public Retailer findRetailerById(int retailerId) throws SQLException;
    public Retailer findRetailerByName(String retailerName) throws SQLException;
    public ObservableList<Retailer> findAllRetailer() throws SQLException ;
}
