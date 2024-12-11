package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.AlertMsg;
import model.CustomModel;
import utils.DBUtil;
import utils.TableUtil;


public class DebugController implements Initializable
{

    @FXML private TextArea f_query;

    @FXML private TableView<CustomModel> tableView;

    private List<TableColumn<CustomModel, String>> listOfColumns = new ArrayList<>();
    private AlertMsg alert;
    private Executor exec;

    private void generateColumns(ResultSetMetaData rsmd)
    {
        try
        {
            for(int i = 0; i < rsmd.getColumnCount(); i++)
            {
                TableColumn<CustomModel, String> col = new TableColumn<>();
                col.setText(rsmd.getColumnName(i + 1));
                listOfColumns.add(col);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addData(ObservableList<CustomModel> listModels)
    {
        try
        {
            tableView.getColumns().setAll(listOfColumns);
            int i = 0;
            tableView.setItems(listModels);
            for(TableColumn<CustomModel, String> col : listOfColumns)
            {
                int j = i;
                col.setCellValueFactory(cellData -> cellData.getValue().getListProp().get(j));
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception
            {
                Platform.runLater(new Runnable() {
                    @Override public void run()
                    {
                        TableUtil.autoResizeColumns(tableView);
                    }
                });
                return null;
            }
        };
        task.setOnFailed(e -> task.getException().printStackTrace());
        exec.execute(task);
    }

    @FXML void execute(ActionEvent event)
    {
        try
        {
            tableView.getColumns().clear();
            listOfColumns.clear();
            ObservableList<CustomModel> listModels = FXCollections.observableArrayList();
            String query = f_query.getText().toLowerCase();
            if(query.contains("insert") || query.contains("update") || query.contains("delete"))
            {
                DBUtil.ExecuteUpdate(query);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully executed!!");
                alert.showAndWait();
                return;
            }
            ResultSet rs = DBUtil.ExecuteQuery(f_query.getText());
            ResultSetMetaData rsmd = rs.getMetaData();
            generateColumns(rsmd);
            int numberOfProps = rsmd.getColumnCount();

            Task<Void> task = new Task<Void>() {
                @Override protected Void call() throws Exception
                {
                    Platform.runLater(new Runnable() {
                        @Override public void run()
                        {
                            try
                            {
                                CustomModel protoModel = new CustomModel(numberOfProps);
                                while(rs.next())
                                {
                                    CustomModel model = protoModel.clone();
                                    for(int i = 0; i < numberOfProps; i++)
                                    {
                                        model.getListProp().get(i).set(rs.getString(i + 1));
                                    }
                                    listModels.add(model);
                                }
                            }
                            catch(SQLException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });
                    return null;
                }
            };

            task.setOnFailed(e -> task.getException().printStackTrace());
            exec.execute(task);
            addData(listModels);
        }
        catch(Exception e)
        {
            alert = new AlertMsg(AlertType.ERROR, e.getMessage());
            alert.getAlert().setResizable(true);
            alert.getAlert().getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
    }
}
