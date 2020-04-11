package team4.personaldietary.persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Retailer;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class RetailerDAOImp implements RetailerDAO {

    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public RetailerDAOImp() {
        super();
    }
    /*
     * This method adds a Retailer object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table.
     *
     * @param Retailer
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int createRetailer(Retailer retailer) throws SQLException {
        int result;
        String createTypeQuery = "INSERT INTO retailer(retailer_name)VALUES (?)";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, retailer.getRetailerName());
            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                retailer.setRetailerId(rs.getInt(1));
            }
        }
        return result;
    }

    @Override
    public Retailer findRetailerById(int retailerId) throws SQLException {
        Retailer found = new Retailer();
        String selectQuery = "SELECT * FROM retailer WHERE retailer_id=?";
        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Using try with resources
        // This ensures that the objects in the parenthesis () will be closed
        // when block ends. In this case the Connection, PreparedStatement and
        // the ResultSet will all be closed.
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             // You must use PreparedStatements to guard against SQL
             // Injection
             PreparedStatement pStatement = connection.prepareStatement(selectQuery);) {
            pStatement.setInt(1, retailerId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found.setRetailerName(resultSet.getString("retailer_name"));
                    found.setRetailerId(retailerId);
                }
            }
        }
        return found;
    }

    @Override
    public Retailer findRetailerByName(String retailerName) throws SQLException {
        Retailer found = new Retailer();
        String selectQuery = "SELECT * FROM retailer WHERE LOWER(retailer_name)=?";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("Error: " + npe.getMessage());
        }

        // Using try with resources
        // This ensures that the objects in the parenthesis () will be closed
        // when block ends. In this case the Connection, PreparedStatement and
        // the ResultSet will all be closed.
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             // You must use PreparedStatements to guard against SQL
             // Injection
             PreparedStatement pStatement = connection.prepareStatement(selectQuery);) {
            pStatement.setString(1, retailerName.toUpperCase());
            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    found.setRetailerId(resultSet.getInt("retailer_id"));
                    found.setRetailerName(retailerName);
                }
            }
        }
        return found;
    }

    /*
     * Retrieve all the records for the given table and returns the data as an
     * Arraylist of Retailer objects
     *
     * @return The Arraylist of Retailer objects
     *
     * @throws SQLException
     */
    @Override
    public List<Retailer> findAllRetailer() throws SQLException {
        System.out.println("RetailerDAOImpl");
        ObservableList<Retailer> rows = FXCollections
                .observableArrayList();
        String selectQuery = "SELECT * FROM retailer";
        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("Error: " + npe.getMessage());
        }

        // Using try with resources
        // This ensures that the objects in the parenthesis () will be closed
        // when block ends. In this case the Connection, PreparedStatement and
        // the ResultSet will all be closed.
        // You must use PreparedStatements to guard against SQL
        // Injection
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement pStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery()) {
            while (resultSet.next()) {
                Retailer found=new Retailer();
                found.setRetailerId(resultSet.getInt("retailer_id"));
                found.setRetailerName(resultSet.getString("retailer_name"));
                rows.add(found);
            }
        }
        return rows;
    }
}
