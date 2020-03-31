package team4.personaldietary.persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.FoodGroup;
import team4.personaldietary.bean.Retailer;
import team4.personaldietary.bean.Type;

import java.io.IOException;
import java.sql.*;

public class FoodGroupDAOImp implements FoodGroupDAO {

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
        FoodGroup found = new FoodGroup();
        String selectQuery = "SELECT * FROM foodgroup WHERE foodgroup_id=?";
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
            pStatement.setInt(1, foodGroupId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found.setFoodGroupName(resultSet.getString("foodgroup_name"));
                    found.setFoodGroupId(foodGroupId);
                }
            }
        }
        return found;
    }

    @Override
    public FoodGroup findFoodGroupByName(String typeName) throws SQLException {
        FoodGroup found = new FoodGroup();
        String selectQuery = "SELECT * FROM foodgroup WHERE UPPER(foodgroup_name)=?";

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
            pStatement.setString(1, typeName.toUpperCase());
            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    found.setFoodGroupId(resultSet.getInt("foodgroup_id"));
                    found.setFoodGroupName(typeName);
                }
            }
        }
        return found;
    }

    /*
     * Retrieve all the records for the given table and returns the data as an
     * Arraylist of FoodGroup objects
     *
     * @return The Arraylist of FoodGroup objects
     *
     * @throws SQLException
     */
    @Override
    public ObservableList<FoodGroup> findAllFoodGroup() throws SQLException {
        System.out.println("FoodGroupDAOImpl");
        ObservableList<FoodGroup> rows = FXCollections
                .observableArrayList();
        String selectQuery = "SELECT * FROM foodgroup";
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
                FoodGroup found=new FoodGroup();
                found.setFoodGroupId(resultSet.getInt("foodgroup_id"));
                found.setFoodGroupName(resultSet.getString("foodgroup_name"));
                rows.add(found);
            }
        }
        return rows;
    }
}
