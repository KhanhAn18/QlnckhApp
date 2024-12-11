package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BoMon;
import utils.DBUtil;


public class BoMonDAO implements DAO<BoMon>
{
    private BoMonDAO()
    {
    }
    public static BoMonDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final BoMonDAO INSTANCE = new BoMonDAO();
    }

    @Override public ObservableList<BoMon> getAll() throws SQLException
    {
        String query = "SELECT * FROM bomon";
        ObservableList<BoMon> boMonList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            boMonList.add(getFromResultSet(resultSet));
        }
        return boMonList;
    }

    private BoMon getFromResultSet(ResultSet resultSet) throws SQLException
    {
        BoMon bomon = new BoMon();
        bomon.setMaKhoa(resultSet.getString("MAKHOA"));
        bomon.setMaBM(resultSet.getString("MABM"));
        bomon.setTenBM(resultSet.getString("TENBM"));
        bomon.setPhong(resultSet.getString("PHONG"));
        bomon.setSdt(resultSet.getString("SDT"));
        bomon.setTruongBM(resultSet.getString("TRGBM"));
        bomon.setNgayNhanChuc(resultSet.getString("NGAYNC"));
        return bomon;
    }

    public ObservableList<BoMon> getFromMaKhoa(String maKhoa) throws SQLException
    {
        String query = "SELECT * FROM bomon WHERE MAKHOA = '" + maKhoa + "'";
        ObservableList<BoMon> boMonList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            boMonList.add(getFromResultSet(resultSet));
        }
        return boMonList;
    }

    @Override public BoMon get(String... id) throws SQLException
    {
        String query = "SELECT * FROM bomon WHERE MABM='" + id[0] + "'";
        BoMon bomon = null;
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        if(resultSet != null)
        {
            resultSet.next();
            bomon = getFromResultSet(resultSet);
        }
        return bomon;
    }

    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM bomon WHERE MABM='" + id[0] + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }

    @Override public void insert(BoMon bomon) throws SQLException
    {
        String query = "INSERT INTO bomon(MABM, TENBM, MAKHOA, PHONG, SDT, TRGBM, NGAYNC) VALUES"
                       + "('" + bomon.getMaBM() + "','" + bomon.getTenBM() + "','" + bomon.getMaKhoa() + "','"
                       + bomon.getPhong() + "','" + bomon.getSdt() + "','" + bomon.getTruongBM() + "','"
                       + bomon.getNgayNhanChuc() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(BoMon bomon) throws SQLException
    {
        String query = "UPDATE bomon SET "
                       + "TENBM='" + bomon.getTenBM() + "',"
                       + "MAKHOA='" + bomon.getMaKhoa() + "',"
                       + "PHONG='" + bomon.getPhong() + "',"
                       + "SDT='" + bomon.getSdt() + "',"
                       + "TRGBM='" + bomon.getTruongBM() + "',"
                       + "NGAYNC='" + bomon.getNgayNhanChuc() + "'"
                       + "WHERE MABM='" + bomon.getMaKhoa() + "'";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id)throws SQLException
    {
        String query = "DELETE FROM bomon WHERE MABM='" + id[0] + "'";
        DBUtil.ExecuteUpdate(query);
    }
}