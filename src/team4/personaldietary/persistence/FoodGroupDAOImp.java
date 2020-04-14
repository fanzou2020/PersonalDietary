package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.FoodGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodGroupDAOImp implements FoodGroupDAO {

    private MyDataSource dataSource = new MyDataSource();
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public FoodGroupDAOImp() {
        super();
    }
    @Override
    public FoodGroup findFoodGroupById(int foodGroupId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FoodGroup found = new FoodGroup();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM foodgroup WHERE foodgroup_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, foodGroupId);
            rs = ps.executeQuery();
            while (rs.next()) {
                found.setFoodGroupName(rs.getString("foodgroup_name"));
                found.setFoodGroupId(foodGroupId);
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
    public FoodGroup findFoodGroupByName(String typeName) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FoodGroup found = new FoodGroup();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM foodgroup WHERE LOWER(foodgroup_name)=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, typeName);
            rs = ps.executeQuery();
            if (rs.next()) {
                found.setFoodGroupId(rs.getInt("foodgroup_id"));
                found.setFoodGroupName(typeName);
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

    /**
     * Retrieve all the records for the given table and returns the data as an
     * Arraylist of FoodGroup objects
     *
     * @return The Arraylist of FoodGroup objects
     *
     * @throws SQLException
     */
    @Override
    public List<FoodGroup> findAllFoodGroup() throws SQLException {
        List<FoodGroup> rows = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM foodgroup";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            while (rs.next()) {
                FoodGroup found = new FoodGroup();
                found.setFoodGroupId(rs.getInt("foodgroup_id"));
                found.setFoodGroupName(rs.getString("foodgroup_name"));
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
