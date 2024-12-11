package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GiaoVienThamGiaDT
{
    private StringProperty maGV;
    private StringProperty maDT;
    private StringProperty STT;
    private StringProperty hoTen;
    private StringProperty phuCap;
    private StringProperty ketQua;

    public GiaoVienThamGiaDT()
    {
        maGV = new SimpleStringProperty();
        maDT = new SimpleStringProperty();
        STT = new SimpleStringProperty();
        hoTen = new SimpleStringProperty();
        phuCap = new SimpleStringProperty();
        ketQua = new SimpleStringProperty();
    }
    // maGV
    public String getMaGV()
    {
        return maGV.get();
    }
    public void setMaGV(String maGV)
    {
        this.maGV.set(maGV);
    }
    public StringProperty maGVProperty()
    {
        return maGV;
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
    // hoTen
    public String getHoTen()
    {
        return hoTen.get();
    }
    public void setHoTen(String hoTen)
    {
        this.hoTen.set(hoTen);
    }
    public StringProperty hoTenProperty()
    {
        return hoTen;
    }
    // phuCap
    public String getPhuCap()
    {
        return phuCap.get();
    }
    public void setPhuCap(String phuCap)
    {
        this.phuCap.set(phuCap);
    }
    public StringProperty phuCapProperty()
    {
        return phuCap;
    }
    // ketQua
    public String getKetQua()
    {
        return ketQua.get();
    }
    public void setKetQua(String ketQua)
    {
        this.ketQua.set(ketQua);
    }
    public StringProperty ketQuaProperty()
    {
        return ketQua;
    }
}