package model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertMsg {
    private Alert alert;
    public AlertMsg()
    {
        alert = new Alert(AlertType.ERROR);
        setTitle(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
    }
    public AlertMsg(AlertType type, String msg)
    {
        alert = new Alert(type);
        setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
    }
    public AlertMsg(AlertType type, String msg, String headerTxt)
    {
        alert = new Alert(type);
        setTitle(type);
        alert.setHeaderText(headerTxt);
        alert.setContentText(msg);
    }
    public void setTitle(AlertType type)
    {
        switch (type) 
        {
            case ERROR:
                alert.setTitle("Error Message");
                break;
            case CONFIRMATION:
                alert.setTitle("Confirmation Message");
                break;
            case INFORMATION:
                alert.setTitle("Information Message");
                break;
            case WARNING:
                alert.setTitle("Warning Message");
                break;
            default:
                alert.setTitle("Message");
                break;
        }
    }

    public Optional<ButtonType> showAndWait()
    {
        return alert.showAndWait();
    }

    public Alert getAlert()
    {
        return alert;
    }
}
