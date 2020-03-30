package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.Type;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface TypeDAO {
    // Create
    public int createType(Type type) throws SQLException;

    // Read
    public Type findTypeById(int typeId) throws SQLException;
    public Type findTypeByName(String typeName) throws SQLException;

}
