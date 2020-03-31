package team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import team4.personaldietary.bean.Meal;
import team4.personaldietary.bean.Retailer;

import java.sql.SQLException;

public interface MealDAO {

    // Create
    public int createMeal(Meal meal) throws SQLException;

    // Read
    public Meal findMealById(int mealId) throws SQLException;
    public Meal findMealByName(String mealName) throws SQLException;
    public ObservableList<Meal> findAllMeal() throws SQLException ;
}
