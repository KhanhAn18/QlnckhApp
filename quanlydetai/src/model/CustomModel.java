package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;


public class CustomModel implements Cloneable
{
    private List<SimpleStringProperty> listProp;

    public CustomModel(int value)
    {
        listProp = new ArrayList<>();
        while(value > 0)
        {
            listProp.add(new SimpleStringProperty());
            value--;
        }
    }
    
    @Override
    public CustomModel clone()
    {
        try
        {
            return new CustomModel(listProp.size());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<SimpleStringProperty> getListProp()
    {
        return listProp;
    }
}
