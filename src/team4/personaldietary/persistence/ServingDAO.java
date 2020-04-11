package team4.personaldietary.persistence;

import team4.personaldietary.bean.Serving;

import java.sql.SQLException;

public interface ServingDAO {
    // add a serving to database
    public int createServing(Serving serving) throws SQLException;

    // find serving by id
    public Serving findServingById(int servingId) throws SQLException;

    //find serving id by given dining id.
    public int findServingIdByDiningId(int diningId) throws SQLException;

    // given serving id, delete serving item.
    public int deleteServing(int servingId) throws SQLException;

}
