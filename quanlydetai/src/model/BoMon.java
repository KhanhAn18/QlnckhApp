package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BoMon
{
    private StringProperty maBM;
    private StringProperty tenBM;
    private StringProperty maKhoa;
    private StringProperty phong;
    private StringProperty sdt;
    private StringProperty truongBM;
    private StringProperty ngayNhanChuc;
    public BoMon()
    {
        maBM = new SimpleStringProperty();
        tenBM = new SimpleStringProperty();
        maKhoa = new SimpleStringProperty();
        phong = new SimpleStringProperty();
        sdt = new SimpleStringProperty();
        truongBM = new SimpleStringProperty();
        ngayNhanChuc = new SimpleStringProperty();
    }
    // maBM
    public String getMaBM()
    {
        return maBM.get();
    }
    public void setMaBM(String maBM)
    {
        this.maBM.set(maBM);
    }
    public StringProperty maBMProperty()
    {
        return maBM;
    }
    // tenBM
    public String getTenBM()
    {
        return tenBM.get();
    }
    public void setTenBM(String tenBM)
    {
        this.tenBM.set(tenBM);
    }
    public StringProperty tenBMProperty()
    {
        return tenBM;
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
    // truongBM
    public String getTruongBM()
    {
        return truongBM.get();
    }
    public void setTruongBM(String truongBM)
    {
        this.truongBM.set(truongBM);
    }
    public StringProperty truongBMProperty()
    {
        return truongBM;
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
