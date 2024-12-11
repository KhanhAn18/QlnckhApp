package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Khoa;
import utils.DBUtil;


public class KhoaDAO implements DAO<Khoa>
{
    private KhoaDAO()
    {
    }
    public static KhoaDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final KhoaDAO INSTANCE = new KhoaDAO();
    }

    @Override public ObservableList<Khoa> getAll() throws SQLException
    {
        String query = "SELECT * FROM khoa";
        ObservableList<Khoa> khoaList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            khoaList.add(getFromResultSet(resultSet));
        }
        return khoaList;
    }

    private Khoa getFromResultSet(ResultSet resultSet) throws SQLException
    {
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(resultSet.getString("MAKHOA"));
        khoa.setTenKhoa(resultSet.getString("TENKHOA"));
        khoa.setNamTL(resultSet.getString("NAMTL"));
        khoa.setPhong(resultSet.getString("PHONG"));
        khoa.setSdt(resultSet.getString("SDT"));
        khoa.setTruongKhoa(resultSet.getString("TRGKHOA"));
        khoa.setNgayNhanChuc(resultSet.getString("NGAYNC"));
        return khoa;
    }

    @Override public Khoa get(String... id) throws SQLException
    {
        String query = "SELECT * FROM khoa WHERE MAKHOA='" + id[0] + "'";
        Khoa khoa = null;
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        if(resultSet != null)
        {
            resultSet.next();
            khoa = getFromResultSet(resultSet);
        }
        return khoa;
    }
    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM khoa WHERE MAKHOA='" + id[0] + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }

    @Override public void insert(Khoa khoa) throws SQLException
    {
        String query = "INSERT INTO khoa(MAKHOA, TENKHOA, NAMTL, PHONG, SDT, TRGKHOA, NGAYNC) VALUES"
                       + "('" + khoa.getMaKhoa() + "','" + khoa.getTenKhoa() + "','" + khoa.getNamTL() + "','"
                       + khoa.getPhong() + "','" + khoa.getSdt() + "','" + khoa.getTruongKhoa() + "','"
                       + khoa.getNgayNhanChuc() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(Khoa khoa) throws SQLException
    {
        String query = "UPDATE khoa SET "
                       + "TENKHOA='" + khoa.getTenKhoa() + "',"
                       + "NAMTL='" + khoa.getNamTL() + "',"
                       + "PHONG='" + khoa.getPhong() + "',"
                       + "SDT='" + khoa.getSdt() + "',"
                       + "TRGKHOA='" + khoa.getTruongKhoa() + "',"
                       + "NGAYNC='" + khoa.getNgayNhanChuc() + "'"
                       + "WHERE MAKHOA='" + khoa.getMaKhoa() + "'";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id) throws SQLException
    {
        String query = "DELETE FROM khoa WHERE MAKHOA='" + id[0] + "'";
        DBUtil.ExecuteUpdate(query);
    }
}
