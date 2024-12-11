package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChuDe;
import utils.DBUtil;


public class ChuDeDAO implements DAO<ChuDe>
{
    private ChuDeDAO()
    {
    }
    public static ChuDeDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final ChuDeDAO INSTANCE = new ChuDeDAO();
    }
    @Override public ObservableList<ChuDe> getAll() throws SQLException
    {
        String query = "SELECT * FROM chude";
        ObservableList<ChuDe> chuDeList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            chuDeList.add(getFromResultSet(resultSet));
        }
        return chuDeList;
    }

    private ChuDe getFromResultSet(ResultSet resultSet) throws SQLException
    {
        ChuDe cd = new ChuDe();
        cd.setMaCD(resultSet.getString("MACD"));
        cd.setTenCD(resultSet.getString("TENCD"));
        return cd;
    }

    @Override public ChuDe get(String... id) throws SQLException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override public void insert(ChuDe cd) throws SQLException
    {
        String query = "INSERT INTO chude(MACD, TENCD) VALUES"
                       + "('" + cd.getMaCD() + "','" + cd.getTenCD() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(ChuDe t)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override public void delete(String... id)throws SQLException
    {
        String query = "DELETE FROM chude WHERE MACD = '" + id[0] + "'";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM chude WHERE MACD='" + id[0] + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
}