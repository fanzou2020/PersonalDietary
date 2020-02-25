package main.team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import main.team4.personaldietary.bean.DiningBean;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface DiningDAO {
    // Create
    int createDining(DiningBean diningBean) throws SQLException;

    // Read
    ArrayList<DiningBean> findDiningByGroup(String group) throws SQLException;

    // Update
    int updateDining(DiningBean diningBean) throws SQLException;

    // Delete
    int deleteDining(int diningId) throws SQLException;

}
