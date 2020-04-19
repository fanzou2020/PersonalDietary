package team4.personaldietary.persistence;

import team4.personaldietary.bean.Meal;

import java.sql.SQLException;
import java.util.List;

public interface MealDAO {

    // Create
    public int createMeal(Meal meal) throws SQLException;

    // Read
    public Meal findMealById(int mealId) throws SQLException;
    public Meal findMealByName(String mealName) throws SQLException;
    public List<Meal> findAllMeal() throws SQLException ;
}
