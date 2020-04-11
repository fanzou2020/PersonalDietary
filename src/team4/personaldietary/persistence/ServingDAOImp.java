package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Serving;

import java.io.IOException;
import java.sql.*;

public class ServingDAOImp implements ServingDAO {
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public ServingDAOImp() { super(); }

    @Override
    public int createServing(Serving serving) throws SQLException {
        int result;
        String createQuery = "INSERT INTO serving(calories, fat, sodium, sugar, amount)VALUES (?,?,?,?,?)";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);) {
            ps.setDouble(1, serving.getCalories());
            ps.setDouble(2, serving.getFat());
            ps.setDouble(3, serving.getSodium());
            ps.setDouble(4, serving.getSugar());
            ps.setString(5, serving.getAmount());
            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                serving.setServingId(rs.getInt(1));
            }
        }
        return result;
    }

    @Override
    public Serving findServingById(int servingId) throws SQLException {
        Serving found = new Serving();
        String selectQuery = "SELECT * FROM serving WHERE serving_id=?";
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
            pStatement.setInt(1, servingId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found.setServingId(servingId);
                    found.setCalories(resultSet.getDouble("calories"));
                    found.setFat(resultSet.getDouble("fat"));
                    found.setSodium(resultSet.getDouble("sodium"));
                    found.setSugar(resultSet.getDouble("sugar"));
                    found.setAmount(resultSet.getString("amount"));
                }
            }
        }
        return found;
    }

    @Override
    public int findServingIdByDiningId(int diningId) throws SQLException {
        int id = -1;
        String selectQuery = "SELECT serving_id FROM dining WHERE dining_id=?";
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
            pStatement.setInt(1, diningId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    id= (resultSet.getInt("serving_id"));
                }
            }
        }
        return id;
    }

    @Override
    public int deleteServing(int servingId) throws SQLException {
        int result = 0;
        Serving find = findServingById(servingId);
        if (find == null) {
            throw new IllegalArgumentException("Can not delete, this serving does not exist.");
        } else {
            String deleteQuery = "DELETE FROM serving WHERE serving_id=?";
            try {
                dcb = pm.loadTextProperties("",filename);
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            } catch (NullPointerException npe) {
                System.out.println("Error: " + npe.getMessage());
            }

            // Using try with resources
            // This ensures that the objects in the parenthesis () will be
            // closed
            // when block ends. In this case the Connection, PreparedStatement
            // and
            // the ResultSet will all be closed.
            try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
                 PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
                ps.setInt(1, servingId);
                result = ps.executeUpdate();
            }
        }
        return result;
    }


}
