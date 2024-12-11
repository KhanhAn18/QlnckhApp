package controller;

import dao.BoMonDAO;
import dao.KhoaDAO;
import java.net.URL;
import java.sql.Date;
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
import model.AlertMsg;
import model.BoMon;
import model.Khoa;
import utils.DateUtil;
import utils.TableUtil;


public class QLKhoaController implements Initializable
{

    @FXML private TableColumn<BoMon, String> bm_maBM;

    @FXML private TableColumn<BoMon, String> bm_ngayNC;

    @FXML private TableColumn<BoMon, String> bm_sdt;

    @FXML private TableColumn<BoMon, String> bm_tenBM;

    @FXML private TableColumn<BoMon, String> bm_trgBM;

    @FXML private TableColumn<BoMon, String> bm_phong;

    @FXML private TextField f_bm_maBM;

    @FXML private DatePicker f_bm_ngNC;

    @FXML private TextField f_bm_phong;

    @FXML private TextField f_bm_sdt;

    @FXML private TextField f_bm_tenBM;

    @FXML private TextField f_bm_trgBM;

    @FXML private TextField f_khoa_maKh;

    @FXML private TextField f_khoa_namTL;

    @FXML private DatePicker f_khoa_ngNC;

    @FXML private TextField f_khoa_phong;

    @FXML private TextField f_khoa_sdt;

    @FXML private TextField f_khoa_trgKh;

    @FXML private TextField f_khoa_tenKh;

    @FXML private TableColumn<Khoa, String> khoa_maKh;

    @FXML private TableColumn<Khoa, String> khoa_namTL;

    @FXML private TableColumn<Khoa, String> khoa_ngayNC;

    @FXML private TableColumn<Khoa, String> khoa_phong;

    @FXML private TableColumn<Khoa, String> khoa_sdt;

    @FXML private TableColumn<Khoa, String> khoa_tenKh;

    @FXML private TableColumn<Khoa, String> khoa_trgKhoa;

    @FXML private TableView<BoMon> tableBM;

    @FXML private TableView<Khoa> tableKhoa;
    private AlertMsg alert;
    private Khoa selectedKhoa;
    private BoMon selectedBoMon;

    public void clearAllField()
    {
        f_bm_maBM.setText("");
        f_bm_ngNC.getEditor().clear();
        f_bm_phong.setText("");
        f_bm_sdt.setText("");
        f_bm_tenBM.setText("");
        f_bm_trgBM.setText("");
        f_khoa_maKh.setText("");
        f_khoa_namTL.setText("");
        f_khoa_tenKh.setText("");
        f_khoa_ngNC.getEditor().clear();
        f_khoa_phong.setText("");
        f_khoa_sdt.setText("");
        f_khoa_trgKh.setText("");
    }
    public void showAllKhoa()
    {
        try
        {
            ObservableList<Khoa> listKhoa = KhoaDAO.Instance().getAll();
            tableKhoa.setItems(listKhoa);
            khoa_maKh.setCellValueFactory(new PropertyValueFactory<>("maKhoa"));
            khoa_namTL.setCellValueFactory(new PropertyValueFactory<>("namTL"));
            khoa_phong.setCellValueFactory(new PropertyValueFactory<>("phong"));
            khoa_ngayNC.setCellValueFactory(new PropertyValueFactory<>("ngayNhanChuc"));
            khoa_tenKh.setCellValueFactory(new PropertyValueFactory<>("tenKhoa"));
            khoa_sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
            khoa_trgKhoa.setCellValueFactory(new PropertyValueFactory<>("truongKhoa"));
            TableUtil.autoResizeColumns(tableKhoa);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showKhoa_BM(String maKhoa)
    {
        try
        {
            if(maKhoa.isEmpty())
            {
                return;
            }
            else
            {
                ObservableList<BoMon> listBM = BoMonDAO.Instance().getFromMaKhoa(maKhoa);
                tableBM.setItems(listBM);
                bm_maBM.setCellValueFactory(new PropertyValueFactory<>("maBM"));
                bm_tenBM.setCellValueFactory(new PropertyValueFactory<>("tenBM"));
                bm_phong.setCellValueFactory(new PropertyValueFactory<>("phong"));
                bm_sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
                bm_trgBM.setCellValueFactory(new PropertyValueFactory<>("truongBM"));
                bm_ngayNC.setCellValueFactory(new PropertyValueFactory<>("ngayNhanChuc"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void clearAll()
    {
        clearAllField();
        tableKhoa.getSelectionModel().clearSelection();
        tableBM.getItems().clear();
        selectedBoMon = null;
        selectedKhoa = null;
    }

    @FXML void deleteBM(ActionEvent event)
    {
        try
        {
            if(f_bm_maBM.getText().isEmpty())
            {
                alert = new AlertMsg();
            }
            else if(selectedKhoa == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Khoa'");
            }
            else if(!BoMonDAO.Instance().isContain(f_bm_maBM.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaBM: " + f_bm_maBM.getText() + "was already exist");
            }
            else
            {
                BoMonDAO.Instance().delete(f_bm_maBM.getText());
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!");
                showKhoa_BM(selectedKhoa.getMaKhoa());
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void deleteKhoa(ActionEvent event)
    {
        try
        {
            if(f_khoa_maKh.getText().isEmpty())
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Khoa'");
            }
            else if(!KhoaDAO.Instance().isContain(f_khoa_maKh.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaKhoa: " + f_khoa_maKh.getText() + " ");
            }
            else
            {
                KhoaDAO.Instance().delete(f_khoa_maKh.getText());
                showAllKhoa();
                clearAll();
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void insertBM(ActionEvent event)
    {
        BoMon boMon = null;
        try
        {
            if(f_bm_maBM.getText().isEmpty() || f_bm_tenBM.getText().isEmpty() || f_bm_phong.getText().isEmpty()
               || f_bm_sdt.getText().isEmpty() || f_bm_trgBM.getText().isEmpty() || f_bm_ngNC.getValue() == null)
            {
                alert = new AlertMsg();
            }
            else if(selectedKhoa == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Khoa'");
            }
            else if(BoMonDAO.Instance().isContain(f_bm_maBM.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaBM: " + f_bm_maBM.getText() + "was already exist");
            }
            else
            {
                boMon = new BoMon();
                boMon.setMaBM(f_bm_maBM.getText());
                boMon.setTenBM(f_bm_tenBM.getText());
                boMon.setPhong(f_bm_phong.getText());
                boMon.setSdt(f_bm_sdt.getText());
                boMon.setTruongBM(f_bm_trgBM.getText());
                Date ngayNC = Date.valueOf(f_bm_ngNC.getValue());
                boMon.setNgayNhanChuc(String.valueOf(ngayNC));
                boMon.setMaKhoa(selectedKhoa.getMaKhoa());

                BoMonDAO.Instance().insert(boMon);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!");
                showKhoa_BM(selectedKhoa.getMaKhoa());
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void insertKhoa(ActionEvent event)
    {
        Khoa khoa = null;
        try
        {
            if(f_khoa_maKh.getText().isEmpty() || f_khoa_tenKh.getText().isEmpty() || f_khoa_phong.getText().isEmpty()
               || f_khoa_sdt.getText().isEmpty() || f_khoa_namTL.getText().isEmpty() || f_khoa_trgKh.getText().isEmpty()
               || f_khoa_ngNC.getValue() == null)
            {
                alert = new AlertMsg();
            }
            else if(KhoaDAO.Instance().isContain(f_khoa_maKh.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaKhoa: " + f_khoa_maKh.getText() + " was already exist!");
            }
            else
            {
                khoa = new Khoa();
                khoa.setMaKhoa(f_khoa_maKh.getText());
                khoa.setTenKhoa(f_khoa_tenKh.getText());
                khoa.setNamTL(f_khoa_namTL.getText());
                khoa.setPhong(f_khoa_phong.getText());
                khoa.setSdt(f_khoa_sdt.getText());
                khoa.setTruongKhoa(f_khoa_trgKh.getText());
                Date ngayNC = Date.valueOf(f_khoa_ngNC.getValue());
                khoa.setNgayNhanChuc(String.valueOf(ngayNC));

                KhoaDAO.Instance().insert(khoa);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!!");
                showAllKhoa();
                clearAll();
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void updateBM(ActionEvent event)
    {
        BoMon boMon = null;
        try
        {
            if(f_bm_maBM.getText().isEmpty() || f_bm_tenBM.getText().isEmpty() || f_bm_phong.getText().isEmpty()
               || f_bm_sdt.getText().isEmpty() || f_bm_trgBM.getText().isEmpty() || f_bm_ngNC.getValue() == null)
            {
                alert = new AlertMsg();
            }
            else if(selectedKhoa == null)
            {
                alert = new AlertMsg(AlertType.ERROR, "Please select 'Khoa'");
            }
            else if(!BoMonDAO.Instance().isContain(f_bm_maBM.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaBM: " + f_bm_maBM.getText() + "was not exist!");
            }
            else
            {
                boMon = new BoMon();
                boMon.setMaBM(f_bm_maBM.getText());
                boMon.setTenBM(f_bm_tenBM.getText());
                boMon.setPhong(f_bm_phong.getText());
                boMon.setSdt(f_bm_sdt.getText());
                boMon.setTruongBM(f_bm_trgBM.getText());
                Date ngayNC = Date.valueOf(f_bm_ngNC.getValue());
                boMon.setNgayNhanChuc(String.valueOf(ngayNC));
                boMon.setMaKhoa(selectedKhoa.getMaKhoa());

                BoMonDAO.Instance().update(boMon);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Updated!");
                showKhoa_BM(selectedKhoa.getMaKhoa());
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void updateKhoa(ActionEvent event)
    {
        Khoa khoa = null;
        try
        {
            if(f_khoa_maKh.getText().isEmpty() || f_khoa_tenKh.getText().isEmpty() || f_khoa_phong.getText().isEmpty()
               || f_khoa_sdt.getText().isEmpty() || f_khoa_namTL.getText().isEmpty() || f_khoa_trgKh.getText().isEmpty()
               || f_khoa_ngNC.getValue() == null)
            {
                alert = new AlertMsg();
            }
            else if(!KhoaDAO.Instance().isContain(f_khoa_maKh.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaKhoa: " + f_khoa_maKh.getText() + " was not exist!");
            }
            else
            {
                khoa = new Khoa();
                khoa.setMaKhoa(f_khoa_maKh.getText());
                khoa.setTenKhoa(f_khoa_tenKh.getText());
                khoa.setNamTL(f_khoa_namTL.getText());
                khoa.setPhong(f_khoa_phong.getText());
                khoa.setSdt(f_khoa_sdt.getText());
                khoa.setTruongKhoa(f_khoa_trgKh.getText());
                Date ngayNC = Date.valueOf(f_khoa_ngNC.getValue());
                khoa.setNgayNhanChuc(String.valueOf(ngayNC));

                KhoaDAO.Instance().update(khoa);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Updated!!");
                showAllKhoa();
                clearAllField();
            }
            alert.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void selectBM(MouseEvent event)
    {
        selectedBoMon = tableBM.getSelectionModel().getSelectedItem();
        int num = tableBM.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_bm_maBM.setText(selectedBoMon.getMaBM());
            f_bm_tenBM.setText(selectedBoMon.getTenBM());
            f_bm_phong.setText(selectedBoMon.getPhong());
            f_bm_sdt.setText(selectedBoMon.getSdt());
            f_bm_trgBM.setText(selectedBoMon.getTruongBM());
            f_bm_ngNC.setValue(DateUtil.parseDate(selectedBoMon.getNgayNhanChuc()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML void selectKhoa(MouseEvent event)
    {
        selectedKhoa = tableKhoa.getSelectionModel().getSelectedItem();
        int num = tableKhoa.getSelectionModel().getSelectedIndex();
        if(num - 1 < -1)
            return;
        try
        {
            f_khoa_maKh.setText(selectedKhoa.getMaKhoa());
            f_khoa_tenKh.setText(selectedKhoa.getTenKhoa());
            f_khoa_namTL.setText(selectedKhoa.getNamTL());
            f_khoa_phong.setText(selectedKhoa.getPhong());
            f_khoa_sdt.setText(selectedKhoa.getSdt());
            f_khoa_trgKh.setText(selectedKhoa.getTruongKhoa());
            f_khoa_ngNC.setValue(DateUtil.parseDate(selectedKhoa.getNgayNhanChuc()));
            showKhoa_BM(selectedKhoa.getMaKhoa());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        showAllKhoa();
    }
}
