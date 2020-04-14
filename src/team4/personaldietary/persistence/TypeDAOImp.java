package team4.personaldietary.persistence;

import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.DBManager.MyDataSource;
import team4.personaldietary.bean.DbConnectionConfigBean;
import team4.personaldietary.bean.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImp implements TypeDAO{

    private MyDataSource dataSource = new MyDataSource();
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();
    private String filename = "jarDbConnection"; // properties file
    // for connecting to
    // the DB

    public TypeDAOImp() {
        super();
    }

    /**
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
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String createTypeQuery = "INSERT INTO type(type_name)VALUES (?)";
            ps = connection.prepareStatement(createTypeQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, type.getTypeName());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                type.setTypeId(rs.getInt(1));
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
    public Type findTypeById(int typeId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Type found = new Type();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM type WHERE type_id=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, typeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                found.setTypeName(rs.getString("type_name"));
                found.setTypeId(typeId);
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
    public Type findTypeByName(String typeName) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Type found = new Type();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM type WHERE LOWER(type_name)=?";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, typeName);
            rs = ps.executeQuery();

            while (rs.next()) {
                found.setTypeId(rs.getInt("type_id"));
                found.setTypeName(typeName);
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
     * Arraylist of Type objects
     *
     * @return The Arraylist of Type objects
     *
     * @throws SQLException
     */
    @Override
    public List<Type> findAllType() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Type> rows = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String selectQuery = "SELECT * FROM type";
            ps = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();

            while (rs.next()) {
                Type found=new Type();
                found.setTypeId(rs.getInt("type_id"));
                found.setTypeName(rs.getString("type_name"));
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
