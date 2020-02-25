package main.team4.personaldietary.persistence;

import javafx.collections.ObservableList;
import main.team4.personaldietary.bean.DiningBean;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiningDAOImpl implements DiningDAO{

    @Override
    public int createDining(DiningBean diningBean) throws SQLException {
        return 0;
    }

    @Override
    public ArrayList<DiningBean> findDiningByGroup(String group) throws SQLException {
        return null;
    }

    @Override
    public int updateDining(DiningBean diningBean) throws SQLException {
        return 0;
    }

    @Override
    public int deleteDining(int diningId) throws SQLException {
        return 0;
    }
}
