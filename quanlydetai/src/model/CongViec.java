package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CongViec {
    private StringProperty maDT;
    private StringProperty STT;
    private StringProperty tenCV;
    private StringProperty ngayBD;
    private StringProperty ngayKT;
    public CongViec()
    {
        maDT = new SimpleStringProperty();
        STT = new SimpleStringProperty();
        tenCV = new SimpleStringProperty();
        ngayBD = new SimpleStringProperty();
        ngayKT = new SimpleStringProperty();
    }
    // maDT
    public String getMaDT()
    {
        return maDT.get();
    }
    public void setMaDT(String maDT)
    {
        this.maDT.set(maDT);
    }
    public StringProperty maDTProperty()
    {
        return maDT;
    }
    // STT
    public String getSTT()
    {
        return STT.get();
    }
    public void setSTT(String STT)
    {
        this.STT.set(STT);
    }
    public StringProperty STTProperty()
    {
        return STT;
    }
    // tenCV
    public String getTenCV()
    {
        return tenCV.get();
    }
    public void setTenCV(String tenCV)
    {
        this.tenCV.set(tenCV);
    }
    public StringProperty tenCVProperty()
    {
        return tenCV;
    }
    // ngayBD
    public String getNgayBD()
    {
        return ngayBD.get();
    }
    public void setNgayBD(String ngayBD)
    {
        this.ngayBD.set(ngayBD);
    }
    public StringProperty ngayBDProperty()
    {
        return ngayBD;
    }
    // ngayKT
    public String getNgayKT()
    {
        return ngayKT.get();
    }
    public void setNgayKT(String ngayKT)
    {
        this.ngayKT.set(ngayKT);
    }
    public StringProperty ngayKTProperty()
    {
        return ngayKT;
    }        
}
