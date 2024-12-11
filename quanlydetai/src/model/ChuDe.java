package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChuDe {
    private StringProperty maCD;
    private StringProperty tenCD;
    public ChuDe()
    {
        maCD = new SimpleStringProperty();
        tenCD = new SimpleStringProperty();
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
    // tenCD
    public String getTenCD()
    {
        return tenCD.get();
    }
    public void setTenCD(String tenCD)
    {
        this.tenCD.set(tenCD);
    }
    public StringProperty tenCDProperty()
    {
        return tenCD;
    }
}