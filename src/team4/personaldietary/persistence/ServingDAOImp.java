package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Serving;

import java.sql.*;

public class ServingDAOImp implements ServingDAO {
    private MyDataSource dataSource = new MyDataSource();
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public ServingDAOImp() { super(); }

    @Override
    public int createServing(Serving serving) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String createQuery = "INSERT INTO serving(calories, fat, sodium, sugar, amount)VALUES (?,?,?,?,?);";
            ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, serving.getCalories());
            ps.setDouble(2, serving.getFat());
            ps.setDouble(3, serving.getSodium());
            ps.setDouble(4, serving.getSugar());
            ps.setString(5, serving.getAmount());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                serving.setServingId(rs.getInt(1));
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
    public Serving findServingById(int servingId) throws SQLException {
        Serving found = new Serving();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM serving WHERE serving_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, servingId);
            rs = ps.executeQuery();
            while (rs.next()) {
                found.setServingId(servingId);
                found.setCalories(rs.getDouble("calories"));
                found.setFat(rs.getDouble("fat"));
                found.setSodium(rs.getDouble("sodium"));
                found.setSugar(rs.getDouble("sugar"));
                found.setAmount(rs.getString("amount"));
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
    public int deleteServing(int servingId) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;

        Serving find = findServingById(servingId);
        if (find == null) {
            throw new IllegalArgumentException("Can not delete, this serving does not exist.");
        }
        else {
            try {
                connection = dataSource.getConnection();
                String deleteQuery = "DELETE FROM serving WHERE serving_id=?";
                ps = connection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, servingId);
                result = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ps != null) ps.close();
                if (connection != null) dataSource.release(connection);
            }
        }
        return result;
    }
}
