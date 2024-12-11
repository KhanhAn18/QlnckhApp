package controller;

import dao.DeTaiDAO;
import dao.GiaoVienDAO;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import model.AlertMsg;
import model.DeTai;
import model.GiaoVien;
import model.SDT;
import utils.DateUtil;
import utils.TableUtil;


public class QLGiaoVienController implements Initializable
{

    @FXML private TableColumn<DeTai, String> dt_maDT;

    @FXML private TableColumn<DeTai, String> dt_tenDt;

    @FXML private TextField f_boMon;

    @FXML private TextField f_diaChi;

    @FXML private ComboBox<String> f_gioiTinh;

    @FXML private TextField f_hoTen;

    @FXML private TextField f_luong;

    @FXML private TextField f_maGV;

    @FXML private DatePicker f_ngSinh;

    @FXML private TextField f_sdt;

    @FXML private TableColumn<GiaoVien, String> gv_boMon;

    @FXML private TableColumn<GiaoVien, String> gv_diaCHi;

    @FXML private TableColumn<GiaoVien, String> gv_gioiTinh;

    @FXML private TableColumn<GiaoVien, String> gv_hoTen;

    @FXML private TableColumn<GiaoVien, String> gv_luong;

    @FXML private TableColumn<GiaoVien, String> gv_maGV;

    @FXML private TableColumn<GiaoVien, String> gv_ngSinh;

    @FXML private TableColumn<SDT, String> sdt;

    @FXML private TableView<DeTai> tableDT;

    @FXML private TableView<GiaoVien> tableGV;

    @FXML private TableView<SDT> tableSDT;
    private List<String> gioiTinhSelect = new ArrayList<>();
    private AlertMsg alert;
    private GiaoVien selectedGV;

    public void showAllGV()
    {
        try
        {
            ObservableList<GiaoVien> listGV = GiaoVienDAO.Instance().getAll();
            tableGV.setItems(listGV);
            gv_maGV.setCellValueFactory(new PropertyValueFactory<>("maGV"));
            gv_hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            gv_gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            gv_ngSinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            gv_diaCHi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            gv_luong.setCellValueFactory(new PropertyValueFactory<>("luong"));
            gv_boMon.setCellValueFactory(new PropertyValueFactory<>("maBM"));
            TableUtil.autoResizeColumns(tableGV);
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
            f_hoTen.setText(selectedGV.getHoTen());
            f_gioiTinh.getSelectionModel().select(gioiTinhSelect.indexOf(selectedGV.getGioiTinh()));
            f_diaChi.setText(selectedGV.getDiaChi());
            f_boMon.setText(selectedGV.getMaBM());
            f_ngSinh.setValue(DateUtil.parseDate(selectedGV.getNgaySinh()));
            f_luong.setText(selectedGV.getLuong());
            showSDT_GV(selectedGV.getMaGV());
            showDT_GV(selectedGV.getMaGV());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_clear()
    {
        clearAllField();
        tableGV.getSelectionModel().clearSelection();
        selectedGV = null;
        tableSDT.getItems().clear();
        tableDT.getItems().clear();

    }

    @FXML void gv_delete(ActionEvent event)
    {
        try
        {
            if(f_maGV.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please fill 'Mã giáo viên'");
                alert.showAndWait();
            }
            else if(!GiaoVienDAO.Instance().isContain(f_maGV.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaGV: " + f_maGV.getText() + " was not exist!");
                alert.showAndWait();
            }
            else
            {
                GiaoVienDAO.Instance().delete(f_maGV.getText());
                showAllGV();
                gv_clear();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_insert(ActionEvent event)
    {
        GiaoVien gv = null;
        try
        {
            if(f_maGV.getText().isEmpty() || f_hoTen.getText().isEmpty() || f_gioiTinh.getSelectionModel() == null
               || f_diaChi.getText().isEmpty() || f_ngSinh.getValue() == null || f_luong.getText().isEmpty()
               || f_boMon.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(GiaoVienDAO.Instance().isContain(f_maGV.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaGV: " + f_maGV.getText() + " was already exist!");
                alert.showAndWait();
            }
            else
            {
                gv = new GiaoVien();
                gv.setMaGV(f_maGV.getText());
                gv.setHoTen(f_hoTen.getText());
                gv.setGioiTinh((String)f_gioiTinh.getSelectionModel().getSelectedItem());
                gv.setDiaChi(f_diaChi.getText());
                Date ngaySinh = Date.valueOf(f_ngSinh.getValue());
                gv.setNgaySinh(String.valueOf(ngaySinh));
                gv.setLuong(f_luong.getText());
                gv.setMaBM(f_boMon.getText());

                GiaoVienDAO.Instance().insert(gv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!");
                alert.showAndWait();
                showAllGV();
                gv_clear();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void gv_update(ActionEvent event)
    {
        GiaoVien gv = null;
        try
        {
            if(f_maGV.getText().isEmpty() || f_hoTen.getText().isEmpty() || f_gioiTinh.getSelectionModel() == null
               || f_diaChi.getText().isEmpty() || f_ngSinh.getValue() == null || f_luong.getText().isEmpty()
               || f_boMon.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(!GiaoVienDAO.Instance().isContain(f_maGV.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaGV: " + f_maGV.getText() + " was not exist!");
                alert.showAndWait();
            }
            else
            {
                gv = new GiaoVien();
                gv.setMaGV(f_maGV.getText());
                gv.setHoTen(f_hoTen.getText());
                gv.setGioiTinh((String)f_gioiTinh.getSelectionModel().getSelectedItem());
                gv.setDiaChi(f_diaChi.getText());
                Date ngaySinh = Date.valueOf(f_ngSinh.getValue());
                gv.setNgaySinh(String.valueOf(ngaySinh));
                gv.setLuong(f_luong.getText());
                gv.setMaBM(f_boMon.getText());
                GiaoVienDAO.Instance().update(gv);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!");
                alert.showAndWait();
                showAllGV();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showSDT_GV(String maGV)
    {
        try
        {
            if(maGV.isEmpty())
            {
                return;
            }
            else
            {
                ObservableList<SDT> listSDT = GiaoVienDAO.Instance().getSDTList(maGV);
                tableSDT.setItems(listSDT);
                sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showDT_GV(String maGV)
    {
        try
        {
            if(maGV.isEmpty())
            {
                return;
            }
            else
            {
                ObservableList<DeTai> listSDT = DeTaiDAO.Instance().getFromMaGV(maGV);
                tableDT.setItems(listSDT);
                dt_maDT.setCellValueFactory(new PropertyValueFactory<>("maDT"));
                dt_tenDt.setCellValueFactory(new PropertyValueFactory<>("tenDT"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void sdt_delete(ActionEvent event)
    {
        try
        {
            if(f_sdt.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please fill 'Số điện thoại' field");
                alert.showAndWait();
            }
            else if(selectedGV == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Giáo viên' in table");
                alert.showAndWait();
            }
            else if(!GiaoVienDAO.Instance().isContainSDT(selectedGV.getMaGV(), f_sdt.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Số điện thoại' was not exist");
                alert.showAndWait();
            }
            else
            {
                GiaoVienDAO.Instance().deleteSDT(selectedGV.getMaGV(), f_sdt.getText());
                showSDT_GV(selectedGV.getMaGV());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void sdt_insert(ActionEvent event)
    {
        try
        {
            if(f_sdt.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please fill 'Số điện thoại' field");
                alert.showAndWait();
            }
            else if(selectedGV == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Giáo viên' in table");
                alert.showAndWait();
            }
            else if(GiaoVienDAO.Instance().isContainSDT(selectedGV.getMaGV(), f_sdt.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "This 'Số điện thoại' was already exist");
                alert.showAndWait();
            }
            else
            {
                GiaoVienDAO.Instance().insertSDT(selectedGV.getMaGV(), f_sdt.getText());
                showSDT_GV(selectedGV.getMaGV());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clearAllField()
    {
        f_maGV.setText("");
        f_hoTen.setText("");
        f_gioiTinh.getSelectionModel().clearSelection();
        f_diaChi.setText("");
        f_ngSinh.getEditor().clear();
        f_luong.setText("");
        f_boMon.setText("");
    }

    public void FDGioiTinh()
    {
        gioiTinhSelect.add("Nam");
        gioiTinhSelect.add("Nữ");
        ObservableList<String> listData = FXCollections.observableArrayList(gioiTinhSelect);
        f_gioiTinh.setItems(listData);
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        showAllGV();
        FDGioiTinh();
    }
}
