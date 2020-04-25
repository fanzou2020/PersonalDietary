package team4.personaldietary.business;

import org.junit.Ignore;
import org.junit.Test;
import team4.personaldietary.DBManager.DbConnectionPropertiesManager;
import team4.personaldietary.bean.*;
import team4.personaldietary.persistence.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCTest {
    private String filename = "jarDbConnection";
    private DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
    private DbConnectionConfigBean dcb = new DbConnectionConfigBean();


    //region test FoodGroupDAO
    @Ignore
    @Test
    public void findFoodGroupByKnownIdTest() throws SQLException {
        FoodGroupDAO foodGroupDAO= new FoodGroupDAOImp();
        int id=3;
        FoodGroup foodGroup =foodGroupDAO.findFoodGroupById(id);
        assertEquals("T3---findFoodGroupByKnownId: ", 3, foodGroup.getFoodGroupId());
    }

    @Ignore
    @Test
    public void findFoodGroupByUnknownIdTest() throws SQLException {
        FoodGroupDAO foodGroupDAO= new FoodGroupDAOImp();
        int id= 10;
        FoodGroup foodGroup =foodGroupDAO.findFoodGroupById(id);
        assertEquals("T3---findFoodGroupByUnknownId: ", -1, foodGroup.getFoodGroupId());
    }

    @Ignore
    @Test
    public void findFoodGroupByNameTest() throws SQLException {
        FoodGroupDAO foodGroupDAO= new FoodGroupDAOImp();
        String name="grain_products";
        FoodGroup foodGroup =foodGroupDAO.findFoodGroupByName(name);
        assertEquals("T3---findFoodGroupByName: ", name, foodGroup.getFoodGroupName());
    }

    @Ignore
    @Test
    public void findAllFoodGroupTest() throws SQLException {
        FoodGroupDAO foodGroupDAO= new FoodGroupDAOImp();

        List<FoodGroup> lmb =foodGroupDAO.findAllFoodGroup();
        assertEquals("T3---findAllFoodGroup: ", 4, lmb.size());
    }
    //endregion

    //region test MealDAO
    @Ignore
    @Test
    public void createMealTest() throws SQLException {
        MealDAO mealDAO= new MealDAOImp();
        Meal meal1=new Meal(), meal2 = new Meal();
        meal1.setMealName("test7");
        meal2.setMealName("test8");

        int result = mealDAO.createMeal(meal1);
        int result2 = mealDAO.createMeal(meal2);
        Meal found= mealDAO.findMealById(meal1.getMealId());
        Meal found2 = mealDAO.findMealById(meal2.getMealId());
        assertEquals("T0---Create Meal", 1, result);
        assertEquals("T0---Create Meal", 1, result2);
        assertEquals("T0---CreateMealTest", found, meal1);
        assertEquals("T0---CreateMealTest", found2, meal2);
    }

    @Ignore
    @Test
    public void findMealByKnownIdTest() throws SQLException {
        MealDAO mealDAO= new MealDAOImp();
        int id=3;
        Meal meal =mealDAO.findMealById(id);
        assertEquals("T3---findMealByKnownIdTest: ", 3, meal.getMealId());
    }

    @Ignore
    @Test
    public void findMealByUnknownIdTest() throws SQLException {
        MealDAO mealDAO= new MealDAOImp();
        int id=15;
        Meal meal =mealDAO.findMealById(id);
        assertEquals("T3---findMealByUnknownIdTest: ", -1, meal.getMealId());
    }

    @Ignore
    @Test
    public void findMealByNameTest() throws SQLException {
        MealDAO mealDAO= new MealDAOImp();
        String mealName="breakfast";
        Meal meal =mealDAO.findMealByName(mealName);
        assertEquals("T3---findMealByNameTest: ", 1, meal.getMealId());
    }

    @Ignore
    @Test
    public void findAllMealTest() throws SQLException {
        MealDAO mealDAO= new MealDAOImp();
        List<Meal> lmb =mealDAO.findAllMeal();
        assertEquals("T3---findAllMealTest: ", 10, lmb.size());
    }
    //endregion

    //region test RetailerDAO
    @Ignore
    @Test
    public void createRetailerTest() throws SQLException {
        RetailerDAO retailerDAO= new RetailerDAOImp();
        Retailer retailer=new Retailer();
        retailer.setRetailerName("test3");

        int result = retailerDAO.createRetailer(retailer);
        Retailer found= retailerDAO.findRetailerById(retailer.getRetailerId());
        assertEquals("T0---Create retailer", 1, result);
        assertEquals("T0---CreateRetailerTest", found, retailer);
    }

    @Ignore
    @Test
    public void findRetailerByKnownIdTest() throws SQLException {
        RetailerDAO retailerDAO= new RetailerDAOImp();
        int id=3;
        Retailer retailer =retailerDAO.findRetailerById(id);
        assertEquals("T3---findRetailerByKnownIdTest: ", 3, retailer.getRetailerId());
    }

    @Ignore
    @Test
    public void findRetailerByUnknownIdTest() throws SQLException {
        RetailerDAO retailerDAO= new RetailerDAOImp();
        int id=15;
        Retailer retailer =retailerDAO.findRetailerById(id);
        assertEquals("T3---findRetailerByUnknownIdTest: ", -1, retailer.getRetailerId());
    }

    @Ignore
    @Test
    public void findRetailerByNameTest() throws SQLException {
        RetailerDAO retailerDAO= new RetailerDAOImp();
        String retailerName="Metro";
        Retailer retailer =retailerDAO.findRetailerByName(retailerName);
        assertEquals("T3---findRetailerByNameTest: ", retailerName, retailer.getRetailerName());
    }

    @Ignore
    @Test
    public void findAllRetailerTest() throws SQLException {
        RetailerDAO retailerDAO= new RetailerDAOImp();
        List<Retailer> lmb =retailerDAO.findAllRetailer();
        assertEquals("T3---findAllRetailerTest: ", 9, lmb.size());
    }
    //endregion

    //region test TypeDAO
    @Ignore
    @Test
    public void createTypeTest() throws SQLException {
        TypeDAO typeDAO= new TypeDAOImp();
        Type type=new Type();
        type.setTypeName("test3");

        int result = typeDAO.createType(type);
        Type found= typeDAO.findTypeById(type.getTypeId());
        assertEquals("T0---Create Type", 1, result);
        assertEquals("T0---createTypeTest", found, type);
    }

    @Ignore
    @Test
    public void findTypeByKnownIdTest() throws SQLException {
        TypeDAO typeDAO= new TypeDAOImp();
        int id=2;
        Type type =typeDAO.findTypeById(id);
        assertEquals("T3---findTypeByKnownIdTest: ", 2, type.getTypeId());
    }

    @Ignore
    @Test
    public void findTypeByUnknownIdTest() throws SQLException {
        TypeDAO typeDAO= new TypeDAOImp();
        int id=15;
        Type type =typeDAO.findTypeById(id);
        assertEquals("T3---findTypeByUnknownIdTest: ", -1, type.getTypeId());
    }

    @Ignore
    @Test
    public void findTypeByNameTest() throws SQLException {
        TypeDAO typeDAO= new TypeDAOImp();
        String typeName="homemade";
        Type type =typeDAO.findTypeByName(typeName);
        assertEquals("T3---findTypeByNameTest: ", typeName, type.getTypeName());
    }

    @Ignore
    @Test
    public void findAllTypeTest() throws SQLException {
        TypeDAO typeDAO= new TypeDAOImp();
        List<Type> lmb =typeDAO.findAllType();
        assertEquals("T3---findAllTypeTest: ", 7, lmb.size());
    }
    //endregion

    //region test DiningDAO
    @Ignore
    @Test
    public void createDiningTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        FoodGroup foodGroup=new FoodGroup("vegetables_and_fruits");
        foodGroup.setFoodGroupId(1);
        Meal meal=new Meal("coffee");
        meal.setMealId(3);
        Type type=new Type("homemade");
        type.setTypeId(1);
        Retailer retailer=new Retailer("McDonald");
        retailer.setRetailerId(4);
        Serving serving = new Serving("1 cup", 10, 10, 10, 10);

        Indining indining=new Indining();
        indining.setName("testIndining");
        indining.setConsumed(false);
        indining.setFoodGroup(foodGroup);
        indining.setMeal(meal);
        indining.setTime(LocalDateTime.now());
        indining.setType(type);
        indining.setServing(serving);

        int inResult = diningDAO.createDining(indining);
        Dining inFound= diningDAO.findDiningById(indining.getDiningId());

        Serving serving2 = new Serving("1 cup", 20, 20, 20, 20);
        Outdining outdining=new Outdining();
        outdining.setName("testOutdining");
        outdining.setConsumed(false);
        outdining.setFoodGroup(foodGroup);
        outdining.setMeal(meal);
        outdining.setTime(LocalDateTime.now());
        outdining.setRetailer(retailer);
        outdining.setServing(serving2);
        int outResult = diningDAO.createDining(outdining);
        Dining outFound= diningDAO.findDiningById(outdining.getDiningId());

        assertEquals("T0---Create Dining", 1, inResult);
        assertEquals("T0---createInDiningTest", inFound, indining);
    }

    @Ignore
    @Test
    public void findDiningByKnownIdTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        int id=19;
        Dining dining =diningDAO.findDiningById(id);
        assertEquals("T3---findDiningByKnownIdTest: ", id, dining.getDiningId());
    }

    @Ignore
    @Test
    public void findDiningByUnknownIdTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        int id=100;
        Dining dining =diningDAO.findDiningById(id);
        assertEquals("T3---findDiningByUnknownIdTest: ", -1, dining.getDiningId());
    }

    @Ignore
    @Test
    public void findDiningByNameTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        String diningName="testOutdining";
        Dining dining =diningDAO.findDiningByName(diningName);
        assertEquals("T3---findDiningByNameTest: ", diningName, dining.getName());
    }

    @Ignore
    @Test
    public void updateDiningTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        Dining dining = new Outdining();
        dining.setDiningId(28);
        dining.setConsumed(true);
        int result = diningDAO.updateDining(dining);
        assertEquals("T---updateDiningTest: ", 1, result);
    }


    @Ignore
    @Test
    public void deleteKnownDiningTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        int result = diningDAO.deleteDining(31);
        assertEquals("T30---deleteKnownDiningTest: ", 1, result);
    }


    @Ignore
    @Test
    public void deleteUnknownDiningTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        int result = diningDAO.deleteDining(50);
        assertEquals("T30---deleteUnknownDiningTest: ", 0, result);
    }


    @Ignore
    @Test
    public void findAllDiningTest() throws SQLException {
        DiningDAO diningDAO= new DiningDAOImp();
        List<Dining> lmb =diningDAO.findAllDining();
        assertEquals("T3---findAllDiningTest: ", 4, lmb.size());
    }
    //endregion

    @Ignore
    @Test
    public void findServingByKnownIdTest() throws SQLException {
        ServingDAO servingDAO = new ServingDAOImp();
        int id=16;
        Serving serving = servingDAO.findServingById(id);
        assertEquals("T3---findServingByKnownIdTest: ", id, serving.getServingId());
    }

    @Ignore
    @Test
    public void findServingByUnknownIdTest() throws SQLException {
        ServingDAO servingDAO = new ServingDAOImp();
        int id=100;
        Serving serving = servingDAO.findServingById(id);
        assertEquals("T3---findServingByUnknownIdTest: ", -1, serving.getServingId());
    }

    @Ignore
    @Test
    public void createServingTest() throws SQLException {
        ServingDAO servingDAO = new ServingDAOImp();
        //Serving serving = new Serving();
        //int id=100;
        int servingDao = servingDAO.createServing(new Serving());
        assertEquals("T3---findServingByUnknownIdTest: ", -1, servingDao);
    }

    @Ignore
    @Test
    public void deleteServingTest() throws SQLException {
        ServingDAO servingDAO = new ServingDAOImp();
        //Serving serving = new Serving();
        int id=100;
        int servingDao = servingDAO.deleteServing(id);
        assertEquals("T3---findServingByUnknownIdTest: ", -1, servingDao);
    }

}
