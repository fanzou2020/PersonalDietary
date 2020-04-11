package team4.personaldietary.persistence;

import javafx.collections.FXCollections;
import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DiningDAOImp implements DiningDAO {

    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    private ServingDAO servingDAO = new ServingDAOImp();
    // for connecting to
    // the DB

    public DiningDAOImp() {
        super();
    }

    @Override
    public int createDining(Dining dining) throws SQLException {
        int result;

        String createQuery = "INSERT INTO dining(dining_name, time, foodgroup_id, meal_id, isConsumed, retailer_id, type_id, serving_id)VALUES (?,?,?,?,?,?,?,?)";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }

        if (dining.getServing().getServingId() == -1) {
            servingDAO.createServing(dining.getServing());
        }

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);) {
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
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dining.setDiningId(rs.getInt(1));
            }
        }
        return result;
    }

    @Override
    public Dining findDiningById(int diningId) throws SQLException {
        Dining found = new Indining();
        String selectQuery = "SELECT * FROM dining d " +
                "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                "INNER JOIN type t ON d.type_id = t.type_id " +
                "WHERE dining_id=?";
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
                    found = createDiningObject(resultSet);
                }
            }
        }
        return found;
    }

    @Override
    public Dining findDiningByName(String diningName) throws SQLException {
        Dining found = new Indining();
        String selectQuery = "SELECT * FROM dining d " +
                "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                "INNER JOIN type t ON d.type_id = t.type_id " +
                "WHERE dining_name=?";
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
            pStatement.setString(1, diningName);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found = createDiningObject(resultSet);
                }
            }
        }
        return found;
    }

    @Override
    public List<Dining> findAllDining() throws SQLException {
        System.out.println("DiningDAOImpl");
        List<Dining> rows = FXCollections
                .observableArrayList();
        String selectQuery = "SELECT * FROM dining d " +
                "INNER JOIN foodgroup f ON d.foodgroup_id = f.foodgroup_id " +
                "INNER JOIN meal m ON d.meal_id = m.meal_id " +
                "INNER JOIN serving s ON d.serving_id = s.serving_id " +
                "INNER JOIN retailer r ON d.retailer_id = r.retailer_id " +
                "INNER JOIN type t ON d.type_id = t.type_id";
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
                rows.add(createDiningObject(resultSet));
            }
        }
        return rows;
    }

    /*
     * This method will update dining.
     *
     * @param Dining
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int updateDining(Dining dining) throws SQLException {
        int result = 0;
        int id = dining.getDiningId();
        Dining find = findDiningById(id);
        if (find == null) {
            throw new IllegalArgumentException("Can not update, this email does not exist.");
        } else {
            String updateQuery = "UPDATE dining SET isConsumed=? WHERE dining_id = ?";

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
                 PreparedStatement ps = connection.prepareStatement(updateQuery);) {
                ps.setBoolean(1, dining.isConsumed());
                ps.setInt(2, dining.getDiningId());
                result = ps.executeUpdate();
            }
        }
        return result;
    }


    @Override
    public int deleteDining(int diningId) throws SQLException {
        int result;
        int serving_id;
        Dining find = findDiningById(diningId);
        if (find == null) {
            throw new IllegalArgumentException("Can not delete, this dining does not exist.");
        } else {
            String deleteQuery = "DELETE FROM dining WHERE dining_id=?";
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
                ps.setInt(1, diningId);
//                deleteServing(findServingIdByDiningId(diningId));
                serving_id = findDiningById(diningId).getServing().getServingId();
                result = ps.executeUpdate();
            }
        }
        if (result == 1) {
            servingDAO.deleteServing(serving_id);
        }
        return result;
    }

//    public int createServing(Serving serving) throws SQLException {
//        int result;
//        String createQuery = "INSERT INTO serving(calories, fat, sodium, sugar, amount)VALUES (?,?,?,?,?)";
//
//        try {
//            dcb = pm.loadTextProperties("",filename);
//        } catch (IOException | NullPointerException ioe) {
//            System.out.println("Error: " + ioe.getMessage());
//        }
//
//        // Connection is only open for the operation and then immediately closed
//        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
//             PreparedStatement ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);) {
//            ps.setDouble(1, serving.getCalories());
//            ps.setDouble(2, serving.getFat());
//            ps.setDouble(3, serving.getSodium());
//            ps.setDouble(4, serving.getSugar());
//            ps.setString(5, serving.getAmount());
//            result = ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                serving.setServingId(rs.getInt(1));
//            }
//        }
//        return result;
//    }
//
//
//    @Override
//    public Serving findServingById(int servingId) throws SQLException {
//        Serving found = new Serving();
//        String selectQuery = "SELECT * FROM serving WHERE serving_id=?";
//        try {
//            dcb = pm.loadTextProperties("",filename);
//        } catch (IOException ioe) {
//            System.out.println("Error: " + ioe.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        // Using try with resources
//        // This ensures that the objects in the parenthesis () will be closed
//        // when block ends. In this case the Connection, PreparedStatement and
//        // the ResultSet will all be closed.
//        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
//             // You must use PreparedStatements to guard against SQL
//             // Injection
//             PreparedStatement pStatement = connection.prepareStatement(selectQuery);) {
//            pStatement.setInt(1, servingId);
//            try (ResultSet resultSet = pStatement.executeQuery();) {
//                while (resultSet.next()) {
//                    found.setServingId(servingId);
//                    found.setCalories(resultSet.getDouble("calories"));
//                    found.setFat(resultSet.getDouble("fat"));
//                    found.setSodium(resultSet.getDouble("sodium"));
//                    found.setSugar(resultSet.getDouble("sugar"));
//                    found.setAmount(resultSet.getString("amount"));
//                }
//            }
//        }
//        return found;
//    }
//
//    @Override
//    public int findServingIdByDiningId(int diningId) throws SQLException {
//        int id = -1;
//        String selectQuery = "SELECT serving_id FROM dining WHERE dining_id=?";
//        try {
//            dcb = pm.loadTextProperties("",filename);
//        } catch (IOException ioe) {
//            System.out.println("Error: " + ioe.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        // Using try with resources
//        // This ensures that the objects in the parenthesis () will be closed
//        // when block ends. In this case the Connection, PreparedStatement and
//        // the ResultSet will all be closed.
//        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
//             // You must use PreparedStatements to guard against SQL
//             // Injection
//             PreparedStatement pStatement = connection.prepareStatement(selectQuery);) {
//            pStatement.setInt(1, diningId);
//            try (ResultSet resultSet = pStatement.executeQuery();) {
//                while (resultSet.next()) {
//                    id= (resultSet.getInt("serving_id"));
//                }
//            }
//        }
//        return id;
//    }
//
//    @Override
//    public int deleteServing(int servingId) throws SQLException {
//        int result = 0;
//        Serving find = findServingById(servingId);
//        if (find == null) {
//            throw new IllegalArgumentException("Can not delete, this serving does not exist.");
//        } else {
//            String deleteQuery = "DELETE FROM serving WHERE serving_id=?";
//            try {
//                dcb = pm.loadTextProperties("",filename);
//            } catch (IOException ioe) {
//                System.out.println("Error: " + ioe.getMessage());
//            } catch (NullPointerException npe) {
//                System.out.println("Error: " + npe.getMessage());
//            }
//
//            // Using try with resources
//            // This ensures that the objects in the parenthesis () will be
//            // closed
//            // when block ends. In this case the Connection, PreparedStatement
//            // and
//            // the ResultSet will all be closed.
//            try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
//                 PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
//                ps.setInt(1, servingId);
//                result = ps.executeUpdate();
//            }
//        }
//        return result;
//    }

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
