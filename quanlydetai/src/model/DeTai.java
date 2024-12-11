package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeTai {
    private StringProperty maDT;
    private StringProperty tenDT;
    private StringProperty capQL;
    private StringProperty kinhPhi;
    private StringProperty ngayBD;
    private StringProperty ngayKT;
    private StringProperty maCD;
    public DeTai()
    {
        this.maDT = new SimpleStringProperty();
        this.tenDT = new SimpleStringProperty();
        this.capQL = new SimpleStringProperty();
        this.kinhPhi = new SimpleStringProperty();
        this.ngayBD = new SimpleStringProperty();
        this.ngayKT = new SimpleStringProperty();
        this.maCD = new SimpleStringProperty();
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
    // tenDT
    public String getTenDT()
    {
        return tenDT.get();
    }
    public void setTenDT(String tenDT)
    {
        this.tenDT.set(tenDT);
    }
    public StringProperty tenDTProperty()
    {
        return tenDT;
    }
    // capQL
    public String getCapQL()
    {
        return capQL.get();
    }
    public void setCapQL(String capQL)
    {
        this.capQL.set(capQL);
    }
    public StringProperty capQLProperty()
    {
        return capQL;
    }
    // kinhPhi
    public String getKinhPhi()
    {
        return kinhPhi.get();
    }
    public void setKinhPhi(String kinhPhi)
    {
        this.kinhPhi.set(kinhPhi);
    }
    public StringProperty kinhPhiProperty()
    {
        return kinhPhi;
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
    // maCD
    public String getMaCD()
    {
        return maCD.get();
    }
    public void setMaCD(String maCD)
    {
        this.maCD.set(maCD);
    }
    public StringProperty maCDProperty()
    {
        return maCD;
    }
}
