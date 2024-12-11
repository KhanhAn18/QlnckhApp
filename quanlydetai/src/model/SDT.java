package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SDT
{
    private StringProperty sdt;
    public SDT()
    {
        sdt = new SimpleStringProperty();
    }
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
}
