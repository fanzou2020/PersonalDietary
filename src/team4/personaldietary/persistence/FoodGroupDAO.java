package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.FoodGroup;
import team4.personaldietary.bean.Type;

import java.sql.SQLException;

public interface FoodGroupDAO {
    // Read
    public FoodGroup findFoodGroupById(int typeId) throws SQLException;
    public FoodGroup findFoodGroupByName(String typeName) throws SQLException;
    public ObservableList<FoodGroup> findAllFoodGroup() throws SQLException;
}
