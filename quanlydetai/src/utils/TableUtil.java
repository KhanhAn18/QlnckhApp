package utils;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class TableUtil
{
    public static void autoResizeColumns(TableView<?> table)
    {
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach(col -> {
            Text t = new Text(col.getText());
            double max = t.getLayoutBounds().getWidth();
            for(int i = 0; i < table.getItems().size(); i++)
            {
                if(col.getCellData(i) != null)
                {
                    t = new Text(col.getCellData(i).toString());
                    double calcWidth = t.getLayoutBounds().getWidth();
                    if(calcWidth > max)
                    {
                        max = calcWidth;
                    }
                }
            }
            col.setPrefWidth(max + 30.0d);
        });
    }
}