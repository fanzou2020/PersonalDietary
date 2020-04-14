package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiningDAOImp implements DiningDAO {

    private MyDataSource dataSource = new MyDataSource();
    private ServingDAO servingDAO = new ServingDAOImp();

    public DiningDAOImp() {
        super();
    }

    @Override
    public int createDining(Dining dining) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // first create serving in database.
        if (dining.getServing().getServingId() == -1) {
            servingDAO.createServing(dining.getServing());
        }

        try {
            connection = dataSource.getConnection();
            String createQuery = "INSERT INTO dining(dining_name, time, foodgroup_id, meal_id, isConsumed, retailer_id, type_id, serving_id)VALUES (?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dining.getName());
            ps.setTimestamp(2, Timestamp.valueOf(dining.getTime()));
            ps.setInt(3, dining.getFoodGroup().getFoodGroupId());
            ps.setInt(4, dining.getMeal().getMealId());
            ps.setBoolean(5, false);
            if(dining instanceof Indining)
            {
                ps.setInt(6, 0);
                ps.setInt(7, ((Indining) dining).getType().getTypeId());
            }
            else if(dining instanceof Outdining){
                ps.setInt(6, ((Outdining) dining).getRetailer().getRetailerId());
                ps.setInt(7, 0);
            }
            ps.setInt(8, dining.getServing().getServingId());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dining.setDiningId(rs.getInt(1));
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
    public Dining findDiningById(int diningId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dining found = new Indining();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM dining d " +
                    "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                    "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                    "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                    "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                    "INNER JOIN type t ON d.type_id = t.type_id " +
                    "WHERE dining_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, diningId);
            rs = ps.executeQuery();
            while (rs.next()) {
                found = createDiningObject(rs);
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
    public Dining findDiningByName(String diningName) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dining found = new Dining();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM dining d " +
                    "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                    "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                    "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                    "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                    "INNER JOIN type t ON d.type_id = t.type_id " +
                    "WHERE dining_name=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, diningName);
            rs = ps.executeQuery();
            while (rs.next()) {
                found = createDiningObject(rs);
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
    public List<Dining> findAllDining() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dining> rows = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM dining d " +
                    "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                    "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                    "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                    "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                    "INNER JOIN type t ON d.type_id = t.type_id";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            while (rs.next()) {
                rows.add(createDiningObject(rs));
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

    /**
     * This method will update dining.
     *
     * @param dining
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int updateDining(Dining dining) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;

        int id = dining.getDiningId();
        Dining find = findDiningById(id);
        if (find == null) {
            throw new IllegalArgumentException("Can not update, this dining item does not exist.");
        }
        else {
            try {
                connection = dataSource.getConnection();
                String updateQuery = "UPDATE dining SET isConsumed=? WHERE dining_id = ?";
                ps = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setBoolean(1, dining.isConsumed());
                ps.setInt(2, dining.getDiningId());
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


    @Override
    public int deleteDining(int diningId) throws SQLException {
        int result = 0, serving_id = -1;
        Connection connection = null;
        PreparedStatement ps = null;

        Dining find = findDiningById(diningId);
        if (find == null) {
            throw new IllegalArgumentException("Can not delete, this dining does not exist.");
        }
        else {
            try {
                connection = dataSource.getConnection();
                String deleteQuery = "DELETE FROM dining WHERE dining_id=?";
                ps = connection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, diningId);
                serving_id = findDiningById(diningId).getServing().getServingId();
                result = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ps != null) ps.close();
                if (connection != null) dataSource.release(connection);
            }
        }
        if (result == 1) {
            servingDAO.deleteServing(serving_id);
        }

        return result;
    }


    private Dining createDiningObject(ResultSet resultSet) throws SQLException {

        FoodGroup foodGroup= new FoodGroup();
        foodGroup.setFoodGroupId(resultSet.getInt("f.foodgroup_id"));
        foodGroup.setFoodGroupName(resultSet.getString("f.foodgroup_name"));

        Meal meal =new Meal();
        meal.setMealId(resultSet.getInt("m.meal_id"));
        meal.setMealName(resultSet.getString("m.meal_name"));

        Serving serving=new Serving();
        serving.setServingId(resultSet.getInt("s.serving_id"));
        serving.setCalories(resultSet.getDouble("s.calories"));
        serving.setFat(resultSet.getDouble("s.fat"));
        serving.setSodium(resultSet.getDouble("s.sodium"));
        serving.setSugar(resultSet.getDouble("s.sugar"));
        serving.setAmount(resultSet.getString("s.amount"));

        if(resultSet.getInt("t.type_id")!=0)
        {
            Type type=new Type();
            type.setTypeId(resultSet.getInt("t.type_id"));
            type.setTypeName(resultSet.getString("t.type_name"));

            Dining dining= new Indining(resultSet.getString("dining_name"),
                    resultSet.getTimestamp("time").toLocalDateTime(),
                    foodGroup,
                    serving,
                    meal,
                    type);
            dining.setDiningId(resultSet.getInt("dining_id"));
            dining.setConsumed(resultSet.getBoolean("isConsumed"));
            return dining;
        }
        if(resultSet.getInt("r.retailer_id")!=0 )
        {
            Retailer retailer=new Retailer();
            retailer.setRetailerId(resultSet.getInt("r.retailer_id"));
            retailer.setRetailerName(resultSet.getString("r.retailer_name"));

            Dining dining= new Outdining(resultSet.getString("dining_name"),
                    resultSet.getTimestamp("time").toLocalDateTime(),
                    foodGroup,
                    serving,
                    meal,
                    retailer);
            dining.setDiningId(resultSet.getInt("dining_id"));
            dining.setConsumed(resultSet.getBoolean("isConsumed"));
            return dining;
        }
        return null;
    }
}
