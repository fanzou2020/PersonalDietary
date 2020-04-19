package team4.personaldietary.persistence;

import team4.personaldietary.bean.FoodGroup;

import java.sql.SQLException;
import java.util.List;

public interface FoodGroupDAO {
    // Read
    public FoodGroup findFoodGroupById(int typeId) throws SQLException;
    public FoodGroup findFoodGroupByName(String typeName) throws SQLException;
    public List<FoodGroup> findAllFoodGroup() throws SQLException;
}
