package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImp implements MealDAO {
    private MyDataSource dataSource = new MyDataSource();

    public MealDAOImp() {
        super();
    }

    /**
     * This method adds a Meal object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table.
     *
     * @param meal
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int createMeal(Meal meal) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();   // get connection from connection pool
            String createTypeQuery = "INSERT INTO meal(meal_name)VALUES (?)";
            ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, meal.getMealName());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                meal.setMealId(rs.getInt(1));
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
    public Meal findMealById(int mealId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Meal found = new Meal();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM meal WHERE meal_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, mealId);
            rs = ps.executeQuery();

            while (rs.next()) {
                found.setMealName(rs.getString("meal_name"));
                found.setMealId(mealId);
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
    public Meal findMealByName(String mealName) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Meal found = new Meal();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM meal WHERE LOWER(meal_name)=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mealName);
            rs = ps.executeQuery();

            if (rs.next()) {
                found.setMealId(rs.getInt("meal_id"));
                found.setMealName(mealName);
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
     * Arraylist of Meal objects
     *
     * @return The Arraylist of Meal objects
     *
     * @throws SQLException
     */
    @Override
    public List<Meal> findAllMeal() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Meal> rows = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM meal";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Meal found = new Meal();
                found.setMealId(rs.getInt("meal_id"));
                found.setMealName(rs.getString("meal_name"));
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
