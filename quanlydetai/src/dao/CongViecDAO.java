package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CongViec;
import utils.DBUtil;


public class CongViecDAO implements DAO<CongViec>
{
    private CongViecDAO()
    {
    }
    public static CongViecDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final CongViecDAO INSTANCE = new CongViecDAO();
    }

    @Override public ObservableList<CongViec> getAll() throws SQLException
    {
        String query = "SELECT * FROM congviec";
        ObservableList<CongViec> congViecList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            congViecList.add(getFromResultSet(resultSet));
        }
        return congViecList;
    }

    private CongViec getFromResultSet(ResultSet resultSet) throws SQLException
    {
        CongViec cv = new CongViec();
        cv.setMaDT(resultSet.getString("MADT"));
        cv.setSTT(resultSet.getString("STT"));
        cv.setTenCV(resultSet.getString("TENCV"));
        cv.setNgayBD(resultSet.getString("NGAYBD"));
        cv.setNgayKT(resultSet.getString("NGAYKT"));
        return cv;
    }

    @Override public CongViec get(String... id) throws SQLException
    {
        String query = "SELECT * FROM congviec "
                       + "WHERE MADT='" + id[0] + "' AND STT=" + id[1];
        CongViec cv = null;
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        if(resultSet != null)
        {
            resultSet.next();
            cv = getFromResultSet(resultSet);
        }
        return cv;
    }

    @Override public void insert(CongViec cv) throws SQLException
    {
        String query = "INSERT INTO congviec(MADT, STT, TENCV, NGAYBD, NGAYKT) VALUES"
                       + "('" + cv.getMaDT() + "'," + cv.getSTT() + ",'" + cv.getTenCV() + "','" + cv.getNgayBD()
                       + "','" + cv.getNgayKT() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(CongViec cv) throws SQLException
    {
        String query = "UPDATE congviec SET "
                       + "TENCV='" + cv.getTenCV() + "',"
                       + "NGAYBD='" + cv.getNgayBD() + "',"
                       + "NGAYKT='" + cv.getNgayKT() + "' "
                       + "WHERE MADT='" + cv.getMaDT() + "' AND STT=" + cv.getSTT();
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id) throws SQLException
    {
        String query = "DELETE FROM congviec "
                       + "WHERE MADT='" + id[0] + "' AND STT=" + id[1];
        DBUtil.ExecuteUpdate(query);
    }

    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM congviec "
                       + "WHERE MADT='" + id[0] + "' AND STT=" + id[1];
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
}