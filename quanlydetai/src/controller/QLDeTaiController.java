package controller;

import dao.ChuDeDAO;
import dao.DeTaiDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.AlertMsg;
import model.ChuDe;
import model.DeTai;
import utils.DateUtil;
import utils.TableUtil;


public class QLDeTaiController implements Initializable
{

    @FXML private TableColumn<ChuDe, String> cd_maCD;

    @FXML private TableColumn<ChuDe, String> cd_tenCD;

    @FXML private TableColumn<DeTai, String> dt_capQL;

    @FXML private TableColumn<DeTai, String> dt_kinhPhi;

    @FXML private TableColumn<DeTai, String> dt_maCD;

    @FXML private TableColumn<DeTai, String> dt_maDT;

    @FXML private TableColumn<DeTai, String> dt_ngayBD;

    @FXML private TableColumn<DeTai, String> dt_ngayKT;

    @FXML private TableColumn<DeTai, String> dt_tenDT;

    @FXML private TextField f_capQL;

    @FXML private TextField f_kinhPhi;

    @FXML private TextField f_maCD;

    @FXML private TextField f_maDT;

    @FXML private DatePicker f_ngayBD;

    @FXML private DatePicker f_ngayKT;

    @FXML private TextField f_tenCD;

    @FXML private TextField f_tenDT;

    @FXML private AnchorPane home_form;

    @FXML private TableView<ChuDe> tableCD;

    @FXML private TableView<DeTai> tableDT;

    private AlertMsg alert;
    private DeTai selectedDT;
    private ChuDe selectedCD;

    public void showAllDT()
    {
        try
        {
            ObservableList<DeTai> listDT = DeTaiDAO.Instance().getAll();
            tableDT.setItems(listDT);
            dt_maDT.setCellValueFactory(new PropertyValueFactory<>("maDT"));
            dt_tenDT.setCellValueFactory(new PropertyValueFactory<>("tenDT"));
            dt_capQL.setCellValueFactory(new PropertyValueFactory<>("capQL"));
            dt_kinhPhi.setCellValueFactory(new PropertyValueFactory<>("kinhPhi"));
            dt_ngayBD.setCellValueFactory(new PropertyValueFactory<>("ngayBD"));
            dt_ngayKT.setCellValueFactory(new PropertyValueFactory<>("ngayKT"));
            dt_maCD.setCellValueFactory(new PropertyValueFactory<>("maCD"));
            TableUtil.autoResizeColumns(tableDT);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showAllCD()
    {
        try
        {
            ObservableList<ChuDe> listCD = ChuDeDAO.Instance().getAll();
            tableCD.setItems(listCD);
            cd_maCD.setCellValueFactory(new PropertyValueFactory<>("maCD"));
            cd_tenCD.setCellValueFactory(new PropertyValueFactory<>("tenCD"));
            TableUtil.autoResizeColumns(tableCD);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clearAllField()
    {
        f_capQL.setText("");
        f_kinhPhi.setText("");
        f_maCD.setText("");
        f_maDT.setText("");
        f_ngayBD.setValue(null);;
        f_ngayKT.setValue(null);
        f_tenCD.setText("");
        f_tenDT.setText("");
    }
    @FXML void cd_delete(ActionEvent event)
    {
        try
        {
            if(f_maCD.getText().isEmpty() || f_tenCD.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(!ChuDeDAO.Instance().isContain(f_maCD.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaCD: " + f_maCD.getText() + " was not exist!!");
                alert.showAndWait();
            }
            else
            {
                ChuDeDAO.Instance().delete(f_maCD.getText());
                showAllCD();
                clearAll();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void cd_insert(ActionEvent event)
    {
        ChuDe cd = null;
        try
        {
            if(f_maCD.getText().isEmpty() || f_tenCD.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(ChuDeDAO.Instance().isContain(f_maCD.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaCD: " + f_maCD.getText() + " was already exist!!");
                alert.showAndWait();
            }
            else
            {
                cd = new ChuDe();
                cd.setMaCD(f_maCD.getText());
                cd.setTenCD(f_tenCD.getText());

                ChuDeDAO.Instance().insert(cd);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added");
                alert.showAndWait();
                showAllCD();
                clearAll();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void cd_select(MouseEvent event)
    {
        selectedCD = tableCD.getSelectionModel().getSelectedItem();
        int num = tableCD.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_maCD.setText(selectedCD.getMaCD());
            f_tenCD.setText(selectedCD.getTenCD());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void clearAll()
    {
        clearAllField();
        tableDT.getSelectionModel().clearSelection();
        tableCD.getSelectionModel().clearSelection();
        selectedCD = null;
        selectedDT = null;
    }

    @FXML void dt_delete(ActionEvent event)
    {
        try
        {
            if(f_maDT.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please fill 'Mã đề tài'");
                alert.showAndWait();
            }
            else if(!DeTaiDAO.Instance().isContain(f_maDT.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaDT: " + f_maDT.getText() + " was not exist!!");
                alert.showAndWait();
            }
            else
            {
                DeTaiDAO.Instance().delete(f_maDT.getText());
                showAllDT();
                clearAll();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void dt_insert(ActionEvent event)
    {
        DeTai dt = null;
        try
        {
            if(f_maDT.getText().isEmpty() || f_tenDT.getText().isEmpty() || f_capQL.getText().isEmpty()
               || f_kinhPhi.getText().isEmpty() || f_ngayBD.getValue() == null || f_ngayKT.getValue() == null)
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(DeTaiDAO.Instance().isContain(f_maDT.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaDT: " + f_maDT.getText() + " was already exist!!");
                alert.showAndWait();
            }
            else if(selectedCD == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Chủ đề'");
                alert.showAndWait();
            }
            else
            {
                dt = new DeTai();
                dt.setMaDT(f_maDT.getText());
                dt.setTenDT(f_tenDT.getText());
                dt.setCapQL(f_capQL.getText());
                dt.setKinhPhi(f_kinhPhi.getText());
                Date ngayBatDau = Date.valueOf(f_ngayBD.getValue());
                Date ngayKetThuc = Date.valueOf(f_ngayKT.getValue());
                dt.setNgayBD(String.valueOf(ngayBatDau));
                dt.setNgayKT(String.valueOf(ngayKetThuc));
                dt.setMaCD(selectedCD.getMaCD());

                DeTaiDAO.Instance().insert(dt);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added");
                alert.showAndWait();
                showAllDT();
                clearAll();
            }
        }
        catch(Exception e)
        {
            if(e instanceof SQLException)
            {
                alert = new AlertMsg(AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
            else
            {
                e.printStackTrace();
            }
        }
    }

    @FXML void dt_select(MouseEvent event)
    {
        selectedDT = tableDT.getSelectionModel().getSelectedItem();
        int num = tableDT.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_maDT.setText(selectedDT.getMaDT());
            f_tenDT.setText(selectedDT.getTenDT());
            f_capQL.setText(selectedDT.getCapQL());
            f_kinhPhi.setText(selectedDT.getKinhPhi());
            f_ngayBD.setValue(DateUtil.parseDate(selectedDT.getNgayBD()));
            f_ngayKT.setValue(DateUtil.parseDate(selectedDT.getNgayKT()));
            for(ChuDe cd : tableCD.getItems())
            {
                if(cd.getMaCD().equals(selectedDT.getMaCD()))
                {
                    tableCD.getSelectionModel().select(cd);
                }
            }
            cd_select(event);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void dt_update(ActionEvent event)
    {
        DeTai dt = null;
        try
        {
            if(f_maDT.getText().isEmpty() || f_tenDT.getText().isEmpty() || f_capQL.getText().isEmpty()
               || f_kinhPhi.getText().isEmpty() || f_ngayBD.getValue() == null || f_ngayKT.getValue() == null)
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(!DeTaiDAO.Instance().isContain(f_maDT.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaDT: " + f_maDT.getText() + " was not exist!!");
                alert.showAndWait();
            }
            else if(selectedCD == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Chủ đề'");
                alert.showAndWait();
            }
            else
            {
                dt = new DeTai();
                dt.setMaDT(f_maDT.getText());
                dt.setTenDT(f_tenDT.getText());
                dt.setCapQL(f_capQL.getText());
                dt.setKinhPhi(f_kinhPhi.getText());
                Date ngayBatDau = Date.valueOf(f_ngayBD.getValue());
                Date ngayKetThuc = Date.valueOf(f_ngayKT.getValue());
                dt.setNgayBD(String.valueOf(ngayBatDau));
                dt.setNgayKT(String.valueOf(ngayKetThuc));
                dt.setMaCD(selectedCD.getMaCD());

                DeTaiDAO.Instance().update(dt);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Updated");
                alert.showAndWait();
                showAllDT();
                clearAll();
            }
        }
        catch(Exception e)
        {
            if(e instanceof SQLException)
            {
                alert = new AlertMsg(AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
            else
            {
                e.printStackTrace();
            }
        }
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        showAllDT();
        showAllCD();
    }
}
