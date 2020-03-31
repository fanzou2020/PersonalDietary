package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.Type;

import java.sql.SQLException;

public interface TypeDAO {
    // Create
    public int createType(Type type) throws SQLException;

    // Read
    public Type findTypeById(int typeId) throws SQLException;
    public Type findTypeByName(String typeName) throws SQLException;
    public ObservableList<Type> findAllType() throws SQLException;
}
