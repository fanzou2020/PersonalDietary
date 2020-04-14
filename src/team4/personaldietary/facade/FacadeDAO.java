package team4.personaldietary.facade;

import team4.personaldietary.bean.*;
import team4.personaldietary.persistence.*;

import java.sql.SQLException;
import java.util.List;

public class FacadeDAO {
    private DiningDAO diningDAO;
    private FoodGroupDAO foodGroupDAO;
    private MealDAO mealDAO;
    private RetailerDAO retailerDAO;
    private TypeDAO typeDAO;
    private ServingDAO servingDAO;

    public FacadeDAO() {
        diningDAO = new DiningDAOImp();
        foodGroupDAO = new FoodGroupDAOImp();
        mealDAO = new MealDAOImp();
        retailerDAO = new RetailerDAOImp();
        typeDAO = new TypeDAOImp();
        servingDAO = new ServingDAOImp();
    }

    // methods for dining
    public int createDining(Dining dining) throws SQLException {
        return diningDAO.createDining(dining);
    }

    public Dining findDiningById(int diningId) throws SQLException {
        return diningDAO.findDiningById(diningId);
    }

    public Dining findDiningByName(String diningName) throws SQLException {
        return diningDAO.findDiningByName(diningName);
    }

    public List<Dining> findAllDining() throws SQLException {
        return diningDAO.findAllDining();
    }

    public int updateDining(Dining dining) throws SQLException {
        return diningDAO.updateDining(dining);
    }

    public int deleteDining(int diningId) throws SQLException {
        return diningDAO.deleteDining(diningId);
    }


    // methods for foodGroup
    public FoodGroup findFoodGroupById(int typeId) throws SQLException {
        return foodGroupDAO.findFoodGroupById(typeId);
    }

    public FoodGroup findFoodGroupByName(String typeName) throws SQLException {
        return foodGroupDAO.findFoodGroupByName(typeName);
    }

    public List<FoodGroup> findAllFoodGroup() throws SQLException {
        return foodGroupDAO.findAllFoodGroup();
    }


    // methods for Meal
    public int createMeal(Meal meal) throws SQLException {
        return mealDAO.createMeal(meal);
    }

    public Meal findMealById(int mealId) throws SQLException {
        return mealDAO.findMealById(mealId);
    }

    public Meal findMealByName(String mealName) throws SQLException {
        return mealDAO.findMealByName(mealName);
    }

    public List<Meal> findAllMeal() throws SQLException {
        return mealDAO.findAllMeal();
    }


    // methods for retailer
    public int createRetailer(Retailer retailer) throws SQLException {
        return retailerDAO.createRetailer(retailer);
    }

    public Retailer findRetailerById(int retailerId) throws SQLException {
        return retailerDAO.findRetailerById(retailerId);
    }

    public Retailer findRetailerByName(String retailerName) throws SQLException {
        return retailerDAO.findRetailerByName(retailerName);
    }

    public List<Retailer> findAllRetailer() throws SQLException {
        return retailerDAO.findAllRetailer();
    }


    // methods for type
    public int createType(Type type) throws SQLException {
        return typeDAO.createType(type);
    }

    public Type findTypeById(int typeId) throws SQLException {
        return typeDAO.findTypeById(typeId);
    }

    public Type findTypeByName(String typeName) throws SQLException {
        return typeDAO.findTypeByName(typeName);
    }

    public List<Type> findAllType() throws SQLException {
        return typeDAO.findAllType();
    }

    // methods for serving
    public int createServing(Serving serving) throws SQLException {
        return servingDAO.createServing(serving);
    }

    public Serving findServingById(int servingId) throws SQLException {
        return servingDAO.findServingById(servingId);
    }

    // given serving id, delete serving item.
    public int deleteServing(int servingId) throws SQLException {
        return servingDAO.deleteServing(servingId);
    }

}
