package team4.personaldietary.persistence;

import team4.personaldietary.bean.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDAO {
    // Create
    public int createType(Type type) throws SQLException;

    // Read
    public Type findTypeById(int typeId) throws SQLException;
    public Type findTypeByName(String typeName) throws SQLException;
    public List<Type> findAllType() throws SQLException;
}
