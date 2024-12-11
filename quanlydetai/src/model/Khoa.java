package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Khoa
{
    private StringProperty maKhoa;
    private StringProperty tenKhoa;
    private StringProperty namThanhLap;
    private StringProperty phong;
    private StringProperty sdt;
    private StringProperty truongKhoa;
    private StringProperty ngayNhanChuc;

    public Khoa()
    {
        this.maKhoa = new SimpleStringProperty();
        this.tenKhoa = new SimpleStringProperty();
        this.namThanhLap = new SimpleStringProperty();
        this.phong = new SimpleStringProperty();
        this.sdt = new SimpleStringProperty();
        this.truongKhoa = new SimpleStringProperty();
        this.ngayNhanChuc = new SimpleStringProperty();
    }
    // maKhoa
    public String getMaKhoa()
    {
        return maKhoa.get();
    }
    public void setMaKhoa(String maKhoa)
    {
        this.maKhoa.set(maKhoa);
    }
    public StringProperty maKhoaProperty()
    {
        return maKhoa;
    }
    // tenKhoa
    public String getTenKhoa()
    {
        return tenKhoa.get();
    }
    public void setTenKhoa(String tenKhoa)
    {
        this.tenKhoa.set(tenKhoa);
    }
    public StringProperty tenKhoaProperty()
    {
        return tenKhoa;
    }
    // namThanhLap
    public String getNamTL()
    {
        return namThanhLap.get();
    }
    public void setNamTL(String namThanhLap)
    {
        this.namThanhLap.set(namThanhLap);
    }
    public StringProperty namTLProperty()
    {
        return namThanhLap;
    }
    // phong
    public String getPhong()
    {
        return phong.get();
    }
    public void setPhong(String phong)
    {
        this.phong.set(phong);
    }
    public StringProperty phongProperty()
    {
        return phong;
    }
    // sdt
    public String getSdt()
    {
        return sdt.get();
    }
    public void setSdt(String sdt)
    {
        this.sdt.set(sdt);
    }
    public StringProperty sdtProperty()
    {
        return sdt;
    }
    // truongKhoa
    public String getTruongKhoa()
    {
        return truongKhoa.get();
    }
    public void setTruongKhoa(String truongKhoa)
    {
        this.truongKhoa.set(truongKhoa);
    }
    public StringProperty truongKhoaProperty()
    {
        return truongKhoa;
    }
    // ngayNhanChuc
    public String getNgayNhanChuc()
    {
        return ngayNhanChuc.get();
    }
    public void setNgayNhanChuc(String ngayNhanChuc)
    {
        this.ngayNhanChuc.set(ngayNhanChuc);
    }
    public StringProperty ngayNhanChucProperty()
    {
        return ngayNhanChuc;
    }
}
