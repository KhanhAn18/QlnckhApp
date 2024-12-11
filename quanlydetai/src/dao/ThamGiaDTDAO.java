package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GiaoVienThamGiaDT;
import utils.DBUtil;


public class ThamGiaDTDAO implements DAO<GiaoVienThamGiaDT>
{

    private ThamGiaDTDAO()
    {
    }
    public static ThamGiaDTDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final ThamGiaDTDAO INSTANCE = new ThamGiaDTDAO();
    }

    @Override public ObservableList<GiaoVienThamGiaDT> getAll() throws SQLException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override public GiaoVienThamGiaDT get(String... id) throws SQLException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    private GiaoVienThamGiaDT getFromResultSet(ResultSet resultSet) throws SQLException
    {
        GiaoVienThamGiaDT gv = new GiaoVienThamGiaDT();
        gv.setHoTen(resultSet.getString("HOTEN"));
        gv.setMaGV(resultSet.getString("MAGV"));
        gv.setPhuCap(resultSet.getString("PHUCAP"));
        gv.setKetQua(resultSet.getString("KETQUA"));
        return gv;
    }
    @Override public boolean isContain(String... id) throws SQLException
    {
        String query = "SELECT * FROM thamgiadt "
                       + "WHERE MAGV='" + id[0] + "' "
                       + "AND MADT='" + id[1] + "' "
                       + "AND STT=" + id[2];
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }

    @Override public void insert(GiaoVienThamGiaDT gv) throws SQLException
    {
        String query = "INSERT INTO thamgiadt(MAGV, MADT, STT, PHUCAP, KETQUA) VALUES"
                       + "('" + gv.getMaGV() + "','" + gv.getMaDT() + "'," + gv.getSTT() + "," + gv.getPhuCap() + ",'"
                       + gv.getKetQua() + "')";
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void update(GiaoVienThamGiaDT gv) throws SQLException
    {
        String query = "UPDATE thamgiadt SET "
                       + "PHUCAP=" + gv.getPhuCap() + ","
                       + "KETQUA='" + gv.getKetQua() + "' "
                       + "WHERE MAGV='" + gv.getMaGV() + "' " 
                       + "AND MADT='" + gv.getMaDT() + "' " 
                       + "AND STT=" + gv.getSTT();
        DBUtil.ExecuteUpdate(query);
    }

    @Override public void delete(String... id) throws SQLException
    {
        String query = "DELETE FROM thamgiadt "
                       + "WHERE MAGV='" + id[0] + "' " 
                       + "AND MADT='" + id[1] + "' " 
                       + "AND STT=" + id[2];
        DBUtil.ExecuteUpdate(query);
    }

    public ObservableList<GiaoVienThamGiaDT> getListFromDT(String maDT, String STT) throws SQLException
    {
        String query = "SELECT gv.MAGV, gv.HOTEN, t.PHUCAP, t.KETQUA "
                        + "FROM giaovien gv "
                        + "JOIN thamgiadt t "
                        + "ON t.MAGV = gv.MAGV "
                        + "AND t.MADT = '" + maDT + "' " 
                        + "AND t.STT = " + STT;
        ObservableList<GiaoVienThamGiaDT> giaoVienList = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        while(resultSet.next())
        {
            giaoVienList.add(getFromResultSet(resultSet));
        }
        return giaoVienList;
    }
}
