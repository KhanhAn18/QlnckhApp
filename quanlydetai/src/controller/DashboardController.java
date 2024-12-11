package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardController implements Initializable
{

    @FXML private BorderPane borderpane;

    @FXML private Button congviec_btn;

    @FXML private Button debug_btn;

    @FXML private Button detai_btn;

    @FXML private Button giaovien_btn;

    @FXML private Button khoa_btn;

    @FXML private Button logout_btn;

    @FXML private AnchorPane main_form;

    @FXML void handleDebug(ActionEvent event) throws IOException
    {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/DeBug.fxml"));
        borderpane.setCenter(view);
    }

    @FXML void handleShowCongViec(ActionEvent event) throws IOException
    {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLCongViec.fxml"));
        borderpane.setCenter(view);
    }

    @FXML void handleShowDeTai(ActionEvent event) throws IOException
    {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLDeTai.fxml"));
        borderpane.setCenter(view);
    }

    @FXML void handleShowQLGiaoVIen(ActionEvent event) throws IOException
    {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLGiaoVien.fxml"));
        borderpane.setCenter(view);
    }

    @FXML void handleShowQLKhoa(ActionEvent event) throws IOException
    {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLKhoa.fxml"));
        borderpane.setCenter(view);
    }

    @FXML void logout(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/loginPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        logout_btn.getScene().getWindow().hide();
    }

    @Override public void initialize(URL arg0, ResourceBundle arg1)
    {
        AnchorPane view;
        try
        {
            view = FXMLLoader.load(getClass().getResource("../view/QLGiaoVien.fxml"));
            borderpane.setCenter(view);
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}