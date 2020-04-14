package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.Retailer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetailerDAOImp implements RetailerDAO {

    private MyDataSource dataSource = new MyDataSource();

    public RetailerDAOImp() {
        super();
    }

    /**
     * This method adds a Retailer object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table.
     *
     * @param retailer
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int createRetailer(Retailer retailer) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String createTypeQuery = "INSERT INTO retailer(retailer_name)VALUES (?)";
            ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, retailer.getRetailerName());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                retailer.setRetailerId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) dataSource.release(connection);
        }

        return result;
    }

    @Override
    public Retailer findRetailerById(int retailerId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Retailer found = new Retailer();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM retailer WHERE retailer_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, retailerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                found.setRetailerName(rs.getString("retailer_name"));
                found.setRetailerId(retailerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) dataSource.release(connection);
        }

        return found;
    }

    @Override
    public Retailer findRetailerByName(String retailerName) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Retailer found = new Retailer();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM retailer WHERE LOWER(retailer_name)=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, retailerName);
            rs = ps.executeQuery();
            while (rs.next()) {
                found.setRetailerName(retailerName);
                found.setRetailerId(rs.getInt("retailer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) dataSource.release(connection);
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
        List<Retailer> rows = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM retailer";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Retailer found = new Retailer();
                found.setRetailerId(rs.getInt("retailer_id"));
                found.setRetailerName(rs.getString("retailer_name"));
                rows.add(found);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) dataSource.release(connection);
        }

        return rows;
    }
}
