package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Meal;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImp implements MealDAO {
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public MealDAOImp() {
        super();
    }
    /*
     * This method adds a Meal object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table.
     *
     * @param Meal
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int createMeal(Meal meal) throws SQLException {
        int result;
        String createTypeQuery = "INSERT INTO meal(meal_name)VALUES (?)";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, meal.getMealName());
            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                meal.setMealId(rs.getInt(1));
            }
        }
        return result;
    }

    @Override
    public Meal findMealById(int mealId) throws SQLException {
        Meal found = new Meal();
        String selectQuery = "SELECT * FROM meal WHERE meal_id=?";
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
            pStatement.setInt(1, mealId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found.setMealName(resultSet.getString("meal_name"));
                    found.setMealId(mealId);
                }
            }
        }
        return found;
    }

    @Override
    public Meal findMealByName(String mealName) throws SQLException {
        Meal found = new Meal();
        String selectQuery = "SELECT * FROM meal WHERE LOWER(meal_name)=?";

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
            pStatement.setString(1, mealName.toUpperCase());
            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    found.setMealId(resultSet.getInt("meal_id"));
                    found.setMealName(mealName);
                }
            }
        }
        return found;
    }

    /*
     * Retrieve all the records for the given table and returns the data as an
     * Arraylist of Meal objects
     *
     * @return The Arraylist of Meal objects
     *
     * @throws SQLException
     */
    @Override
    public List<Meal> findAllMeal() throws SQLException {
        System.out.println("MealDAOImpl");
        List<Meal> rows = new ArrayList<>();
        String selectQuery = "SELECT * FROM meal";
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
                Meal found=new Meal();
                found.setMealId(resultSet.getInt("meal_id"));
                found.setMealName(resultSet.getString("meal_name"));
                rows.add(found);
            }
        }
        return rows;
    }
}
