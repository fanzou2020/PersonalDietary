package team4.personaldietary.persistence;

import team4.personaldietary.bean.Retailer;

import java.sql.SQLException;
import java.util.List;

public interface RetailerDAO {

    // Create
    public int createRetailer(Retailer retailer) throws SQLException;

    // Read
    public Retailer findRetailerById(int retailerId) throws SQLException;
    public Retailer findRetailerByName(String retailerName) throws SQLException;
    public List<Retailer> findAllRetailer() throws SQLException ;
}
