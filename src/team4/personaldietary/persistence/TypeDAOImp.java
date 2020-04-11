package team4.personaldietary.persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Type;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class TypeDAOImp implements TypeDAO{

    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public TypeDAOImp() {
        super();
    }
    /*
     * This method adds a Type object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table.
     *
     * @param type
     *
     * @return The number of records created, should always be 1
     *
     * @throws SQLException
     */
    @Override
    public int createType(Type type) throws SQLException {
        int result;
        String createTypeQuery = "INSERT INTO type(type_name)VALUES (?)";

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(dcb.getFullUrl()+":"+dcb.getPort()+"/"+dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
             PreparedStatement ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, type.getTypeName());
            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                type.setTypeId(rs.getInt(1));
            }
        }
        return result;
    }

    @Override
    public Type findTypeById(int typeId) throws SQLException {
        Type found = new Type();
        String selectQuery = "SELECT * FROM type WHERE type_id=?";
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
            pStatement.setInt(1, typeId);
            try (ResultSet resultSet = pStatement.executeQuery();) {
                while (resultSet.next()) {
                    found.setTypeName(resultSet.getString("type_name"));
                    found.setTypeId(typeId);
                }
            }
        }
        return found;
    }

    @Override
    public Type findTypeByName(String typeName) throws SQLException {
        Type found = new Type();
        String selectQuery = "SELECT * FROM type WHERE LOWER(type_name)=?";

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
                    found.setTypeId(resultSet.getInt("type_id"));
                    found.setTypeName(typeName);
                }
            }
        }
        return found;
    }

    /*
     * Retrieve all the records for the given table and returns the data as an
     * Arraylist of Type objects
     *
     * @return The Arraylist of Type objects
     *
     * @throws SQLException
     */
    @Override
    public List<Type> findAllType() throws SQLException {
        System.out.println("TypeDAOImpl");
        ObservableList<Type> rows = FXCollections
                .observableArrayList();
        String selectQuery = "SELECT * FROM type";
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
                Type found=new Type();
                found.setTypeId(resultSet.getInt("type_id"));
                found.setTypeName(resultSet.getString("type_name"));
                rows.add(found);
            }
        }
        return rows;
    }
}
