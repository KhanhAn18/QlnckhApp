package controller;

import dao.CongViecDAO;
import dao.ThamGiaDTDAO;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.AlertMsg;
import model.CongViec;
import model.GiaoVienThamGiaDT;
import utils.DateUtil;
import utils.TableUtil;


public class QLCongViecController implements Initializable
{

    @FXML private TableColumn<CongViec, String> cv_maDT;

    @FXML private TableColumn<CongViec, String> cv_ngayBD;

    @FXML private TableColumn<CongViec, String> cv_ngayKT;

    @FXML private TableColumn<CongViec, String> cv_stt;

    @FXML private TableColumn<CongViec, String> cv_tenCV;

    @FXML private ComboBox<String> f_ketQua;

    @FXML private TextField f_maDT;

    @FXML private TextField f_maGV;

    @FXML private DatePicker f_ngayBD;

    @FXML private DatePicker f_ngayKT;

    @FXML private TextField f_phuCap;

    @FXML private TextField f_stt;

    @FXML private TextField f_tenCV;

    @FXML private TableColumn<GiaoVienThamGiaDT, String> gv_hoTen;

    @FXML private TableColumn<GiaoVienThamGiaDT, String> gv_ketQua;

    @FXML private TableColumn<GiaoVienThamGiaDT, String> gv_maGV;

    @FXML private TableColumn<GiaoVienThamGiaDT, String> gv_phuCap;

    @FXML private AnchorPane home_form;

    @FXML private TableView<CongViec> tableCV;

    @FXML private TableView<GiaoVienThamGiaDT> tableGV;

    private AlertMsg alert;
    private CongViec selectedCV;
    private GiaoVienThamGiaDT selectedGV;
    private List<String> ketQuaSelect = new ArrayList<>();

    public void showAllCV()
    {
        try
        {
            ObservableList<CongViec> listCV = CongViecDAO.Instance().getAll();
            tableCV.setItems(listCV);
            cv_maDT.setCellValueFactory(new PropertyValueFactory<>("maDT"));
            cv_stt.setCellValueFactory(new PropertyValueFactory<>("STT"));
            cv_tenCV.setCellValueFactory(new PropertyValueFactory<>("tenCV"));
            cv_ngayBD.setCellValueFactory(new PropertyValueFactory<>("ngayBD"));
            cv_ngayKT.setCellValueFactory(new PropertyValueFactory<>("ngayKT"));
            TableUtil.autoResizeColumns(tableCV);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showCV_GV(String maDT, String STT)
    {
        try
        {
            ObservableList<GiaoVienThamGiaDT> listGV = ThamGiaDTDAO.Instance().getListFromDT(maDT, STT);
            tableGV.setItems(listGV);
            gv_maGV.setCellValueFactory(new PropertyValueFactory<>("maGV"));
            gv_phuCap.setCellValueFactory(new PropertyValueFactory<>("phuCap"));
            gv_hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            gv_ketQua.setCellValueFactory(new PropertyValueFactory<>("ketQua"));
            TableUtil.autoResizeColumns(tableGV);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clearAllField()
    {
        f_maDT.setText("");
        f_phuCap.setText("");
        f_maGV.setText("");
        f_ngayBD.setValue(null);
        f_ngayKT.setValue(null);
        f_ketQua.getSelectionModel().clearSelection();
        f_tenCV.setText("");
        f_stt.setText("");
    }

    @FXML void clearAll()
    {
        clearAllField();
        tableCV.getSelectionModel().clearSelection();
        tableGV.getItems().clear();
        selectedCV = null;
        selectedGV = null;
    }

    @FXML void cv_delete(ActionEvent event)
    {
        try
        {
            if(f_maDT.getText().isEmpty() || f_stt.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Công việc' or fill 'Mã đề tài' and 'STT'");
                alert.showAndWait();
            }
            else if(!CongViecDAO.Instance().isContain(f_maDT.getText(), f_stt.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Công việc' was not exist!");
                alert.showAndWait();
            }
            else
            {
                CongViecDAO.Instance().delete(f_maDT.getText(), f_stt.getText());
                showAllCV();
                clearAll();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void cv_insert(ActionEvent event)
    {
        CongViec cv = null;
        try
        {
            if(f_maDT.getText().isEmpty() || f_stt.getText().isEmpty() || f_tenCV.getText().isEmpty()
               || f_ngayBD.getValue() == null || f_ngayKT.getValue() == null)
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(CongViecDAO.Instance().isContain(f_maDT.getText(), f_stt.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Công việc' was already exist!");
                alert.showAndWait();
            }
            else
            {
                cv = new CongViec();
                cv.setMaDT(f_maDT.getText());
                cv.setSTT(f_stt.getText());
                cv.setTenCV(f_tenCV.getText());
                Date ngayBatDau = Date.valueOf(f_ngayBD.getValue());
                Date ngayKetThuc = Date.valueOf(f_ngayKT.getValue());
                cv.setNgayBD(String.valueOf(ngayBatDau));
                cv.setNgayKT(String.valueOf(ngayKetThuc));

                CongViecDAO.Instance().insert(cv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added");
                alert.showAndWait();
                showAllCV();
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

    @FXML void cv_select(MouseEvent event)
    {
        selectedCV = tableCV.getSelectionModel().getSelectedItem();
        int num = tableCV.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_maDT.setText(selectedCV.getMaDT());
            f_tenCV.setText(selectedCV.getTenCV());
            f_stt.setText(selectedCV.getSTT());
            f_ngayBD.setValue(DateUtil.parseDate(selectedCV.getNgayBD()));
            f_ngayKT.setValue(DateUtil.parseDate(selectedCV.getNgayKT()));
            showCV_GV(selectedCV.getMaDT(), selectedCV.getSTT());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void cv_update(ActionEvent event)
    {
        CongViec cv = null;
        try
        {
            if(f_maDT.getText().isEmpty() || f_stt.getText().isEmpty() || f_tenCV.getText().isEmpty()
               || f_ngayBD.getValue() == null || f_ngayKT.getValue() == null)
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(!CongViecDAO.Instance().isContain(f_maDT.getText(), f_stt.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Công việc' was not exist!");
                alert.showAndWait();
            }
            else
            {
                cv = new CongViec();
                cv.setMaDT(f_maDT.getText());
                cv.setSTT(f_stt.getText());
                cv.setTenCV(f_tenCV.getText());
                Date ngayBatDau = Date.valueOf(f_ngayBD.getValue());
                Date ngayKetThuc = Date.valueOf(f_ngayKT.getValue());
                cv.setNgayBD(String.valueOf(ngayBatDau));
                cv.setNgayKT(String.valueOf(ngayKetThuc));

                CongViecDAO.Instance().update(cv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Updated");
                alert.showAndWait();
                showAllCV();
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

    @FXML void gv_delete(ActionEvent event)
    {
        try
        {
            if(f_maGV.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Giáo viên' or fill 'Mã giáo viên'");
                alert.showAndWait();
            }
            else if(selectedCV == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Công việc'");
                alert.showAndWait();
            }
            else if(!ThamGiaDTDAO.Instance().isContain(f_maGV.getText(), selectedCV.getMaDT(), selectedCV.getSTT()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Giáo viên' was already exist!");
                alert.showAndWait();
            }
            else
            {
                ThamGiaDTDAO.Instance().delete(f_maGV.getText(), selectedCV.getMaDT(), selectedCV.getSTT());
                showCV_GV(selectedCV.getMaDT(), selectedCV.getSTT());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_insert(ActionEvent event)
    {
        GiaoVienThamGiaDT gv = null;
        try
        {
            if(f_maGV.getText().isEmpty() || f_ketQua.getSelectionModel() == null || f_phuCap.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(selectedCV == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Công việc'");
                alert.showAndWait();
            }
            else if(ThamGiaDTDAO.Instance().isContain(f_maGV.getText(), selectedCV.getMaDT(), selectedCV.getSTT()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Giáo viên' was already exist!");
                alert.showAndWait();
            }
            else
            {
                gv = new GiaoVienThamGiaDT();
                gv.setMaGV(f_maGV.getText());
                gv.setMaDT(selectedCV.getMaDT());
                gv.setSTT(selectedCV.getSTT());
                gv.setKetQua(f_ketQua.getSelectionModel().getSelectedItem());
                gv.setPhuCap(f_phuCap.getText());

                ThamGiaDTDAO.Instance().insert(gv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added");
                alert.showAndWait();
                showCV_GV(selectedCV.getMaDT(), selectedCV.getSTT());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_select(MouseEvent event)
    {
        selectedGV = tableGV.getSelectionModel().getSelectedItem();
        int num = tableGV.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_maGV.setText(selectedGV.getMaGV());
            f_phuCap.setText(selectedGV.getPhuCap());
            f_ketQua.getSelectionModel().select(ketQuaSelect.indexOf(selectedGV.getKetQua()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_update(ActionEvent event)
    {
        GiaoVienThamGiaDT gv = null;
        try
        {
            if(f_maGV.getText().isEmpty() || f_phuCap.getText().isEmpty() || f_ketQua.getSelectionModel() == null)
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(selectedCV == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Công việc'");
                alert.showAndWait();
            }
            else if(!ThamGiaDTDAO.Instance().isContain(f_maGV.getText(), selectedCV.getMaDT(), selectedCV.getSTT()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Giáo viên' was not exist!");
                alert.showAndWait();
            }
            else
            {
                gv = new GiaoVienThamGiaDT();
                gv.setMaGV(f_maGV.getText());
                gv.setMaDT(selectedCV.getMaDT());
                gv.setSTT(selectedCV.getSTT());
                gv.setPhuCap(f_phuCap.getText());
                gv.setKetQua(f_ketQua.getSelectionModel().getSelectedItem());

                ThamGiaDTDAO.Instance().update(gv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Updated");
                alert.showAndWait();
                showCV_GV(selectedCV.getMaDT(), selectedCV.getSTT());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void FDKetQua()
    {
        ketQuaSelect.add("Đạt");
        ketQuaSelect.add("Không Đạt");
        ObservableList<String> listData = FXCollections.observableArrayList(ketQuaSelect);
        f_ketQua.setItems(listData);
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        showAllCV();
        FDKetQua();
    }
}
