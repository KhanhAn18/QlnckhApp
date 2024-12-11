package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GiaoVien;
import model.SDT;
import utils.DBUtil;

public class GiaoVienDAO implements DAO<GiaoVien>
{
    private GiaoVienDAO()
    {
    }
    public static GiaoVienDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final GiaoVienDAO INSTANCE = new GiaoVienDAO();
    }

    @Override public ObservableList<GiaoVien> getAll() throws SQLException
    {
        String query = "SELECT * FROM giaovien";
        ObservableList<GiaoVien> giaoVienList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            giaoVienList.add(getFromResultSet(resultSet));
        }
        return giaoVienList;
    }

    private GiaoVien getFromResultSet(ResultSet resultSet) throws SQLException
    {
        GiaoVien gv = new GiaoVien();
        gv.setMaGV(resultSet.getString("MAGV"));
        gv.setHoTen(resultSet.getString("HOTEN"));
        gv.setGioiTinh(resultSet.getString("GT"));
        gv.setNgaySinh(resultSet.getString("NGSINH"));
        gv.setDiaChi(resultSet.getString("DIACHI"));
        gv.setLuong(resultSet.getString("LUONG"));
        gv.setMaBM(resultSet.getString("MABM"));
        return gv;
    }

    @Override public GiaoVien get(String... id) throws SQLException
    {
        String query = "SELECT * FROM giaovien WHERE MAGV='" + id[0] + "'";
        GiaoVien gv = null;
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        if(resultSet != null)
        {
            resultSet.next();
            gv = getFromResultSet(resultSet);
        }
        return gv;
    }
    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM giaovien WHERE MAGV='" + id[0] + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }

    @Override public void insert(GiaoVien gv) throws SQLException
    {
        String query = "INSERT INTO giaovien(MAGV, HOTEN, GT, NGSINH, DIACHI, LUONG, MABM) VALUES"
                       + "('" + gv.getMaGV() + "','" + gv.getHoTen() + "','" + gv.getGioiTinh() + "','"
                       + gv.getNgaySinh() + "','" + gv.getDiaChi() + "'," + gv.getLuong() + ",'" + gv.getMaBM() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(GiaoVien gv) throws SQLException
    {
        String query = "UPDATE giaovien SET "
                       + "HOTEN='" + gv.getHoTen() + "',"
                       + "GT='" + gv.getGioiTinh() + "',"
                       + "NGSINH='" + gv.getNgaySinh() + "',"
                       + "DIACHI='" + gv.getDiaChi() + "',"
                       + "LUONG=" + gv.getLuong() + ","
                       + "MABM='" + gv.getMaBM() + "'"
                       + "WHERE MAGV='" + gv.getMaGV() + "'";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id)throws SQLException
    {
        String query = "DELETE FROM giaovien WHERE MAGV='" + id[0] + "'";
        DBUtil.ExecuteUpdate(query);
    }

    public ObservableList<SDT> getSDTList(String maGV) throws SQLException
    {
        String query = "SELECT SDT FROM gv_dt WHERE MAGV = '" + maGV + "'";
        ObservableList<SDT> sdtList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            SDT sdt = new SDT();
            sdt.setSdt(resultSet.getString(1));
            sdtList.add(sdt);
        }
        return sdtList;
    }

    public void insertSDT(String maGV, String sdt) throws SQLException
    {
        String query = "INSERT INTO gv_dt(MAGV, SDT) VALUES"
                       + "('" + maGV + "','" + sdt + "')";
        DBUtil.ExecuteUpdate(query);
    }

    public void deleteSDT(String maGV, String sdt) throws SQLException
    {
        String query = "DELETE FROM gv_dt WHERE MAGV='" + maGV + "' AND SDT='" + sdt + "'";
        DBUtil.ExecuteUpdate(query);
    }

    public boolean isContainSDT(String maGV, String sdt) throws SQLException
    {
        String query = "SELECT * FROM gv_dt WHERE MAGV='" + maGV + "' AND SDT='" + sdt + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
}
