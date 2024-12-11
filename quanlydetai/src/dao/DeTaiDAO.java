package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DeTai;
import utils.DBUtil;


public class DeTaiDAO implements DAO<DeTai>
{
    private DeTaiDAO()
    {
    }
    public static DeTaiDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final DeTaiDAO INSTANCE = new DeTaiDAO();
    }

    @Override public ObservableList<DeTai> getAll() throws SQLException
    {
        String query = "SELECT * FROM detai";
        ObservableList<DeTai> deTaiList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            deTaiList.add(getFromResultSet(resultSet));
        }
        return deTaiList;
    }

    private DeTai getFromResultSet(ResultSet resultSet) throws SQLException
    {
        DeTai dt = new DeTai();
        dt.setMaDT(resultSet.getString("MADT"));
        dt.setTenDT(resultSet.getString("TENDT"));
        dt.setCapQL(resultSet.getString("CAPQL"));
        dt.setKinhPhi(resultSet.getString("KINHPHI"));
        dt.setNgayBD(resultSet.getString("NGAYBD"));
        dt.setNgayKT(resultSet.getString("NGAYKT"));
        dt.setMaCD(resultSet.getString("MACD"));
        return dt;
    }

    public ObservableList<DeTai> getFromMaGV(String maGV) throws SQLException
    {
        String query = "SELECT d.MADT, d.TENDT "
                       + "FROM detai d "
                       + "JOIN thamgiadt t USING(MADT) "
                       + "WHERE MAGV = '" + maGV + "' "
                       + "GROUP BY d.MADT, d.TENDT";
        ObservableList<DeTai> deTaiList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            DeTai dt = new DeTai();
            dt.setMaDT(resultSet.getString("MADT"));
            dt.setTenDT(resultSet.getString("TENDT"));
            deTaiList.add(dt);
        }
        return deTaiList;
    }

    @Override public DeTai get(String... id) throws SQLException
    {
        String query = "SELECT * FROM detai WHERE MADT='" + id[0] + "'";
        DeTai dt = null;
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        if(resultSet != null)
        {
            resultSet.next();
            dt = getFromResultSet(resultSet);
        }
        return dt;
    }

    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM detai WHERE MADT='" + id[0] + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }

    @Override public void insert(DeTai dt) throws SQLException
    {
        String query = "INSERT INTO detai(MADT, TENDT, CAPQL, KINHPHI, NGAYBD, NGAYKT, MACD) VALUES"
                       + "('" + dt.getMaDT() + "','" + dt.getTenDT() + "','" + dt.getCapQL() + "'," + dt.getKinhPhi()
                       + ",'" + dt.getNgayBD() + "','" + dt.getNgayKT() + "','" + dt.getMaCD() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(DeTai dt) throws SQLException
    {
        String query = "UPDATE detai SET "
                       + "TENDT='" + dt.getTenDT() + "',"
                       + "CAPQL='" + dt.getCapQL() + "',"
                       + "KINHPHI=" + dt.getKinhPhi() + ","
                       + "NGAYBD='" + dt.getNgayBD() + "',"
                       + "NGAYKT='" + dt.getNgayKT() + "',"
                       + "MACD='" + dt.getMaCD() + "'"
                       + "WHERE MADT='" + dt.getMaDT() + "'";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id)throws SQLException
    {
        String query = "DELETE FROM detai WHERE MADT='" + id[0] + "'";
        DBUtil.ExecuteUpdate(query);
    }
}