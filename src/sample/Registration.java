package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {

    @FXML
    private Label lblRegistration;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXTextField txtBirthdate;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtCountry;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtFathername;

    @FXML
    private JFXTextField txtPhonenumber;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtInitialAmount;

    @FXML
    private JFXButton btnDone;

    @FXML
    private Label lblVerify;

    @FXML
    private JFXButton btnVerify;

    @FXML
    void pressBtnDone(ActionEvent event) {
        if(txtFirstname.getText().compareTo("")==0||txtLastname.getText().compareTo("")==0||txtBirthdate.getText().compareTo("")==0||txtGender.getText().compareTo("")==0||txtCountry.getText().compareTo("")==0||txtID.getText().compareTo("")==0||txtPassword.getText().compareTo("")==0||txtInitialAmount.getText().compareTo("")==0||txtFathername.getText().compareTo("")==0||txtPhonenumber.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Compelete all the parts!!");
            alert.showAndWait();
        }else{
            Person client=new Person();
            client.setFirstname(txtFirstname.getText());
            client.setLastname(txtLastname.getText());
            client.setBirthDate(txtBirthdate.getText());
            client.setGender(txtGender.getText());
            client.setCountry(txtCountry.getText());
            client.setID(txtID.getText());
            client.setFathername(txtFathername.getText());
            client.setPhonenumber(txtPhonenumber.getText());
            client.setPassword(txtPassword.getText());
            client.setInitialAmount(txtInitialAmount.getText());
            DataBase db=new DataBase();
            db.insertClient(client, client.getID());

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Successfully Registered!!");
            alert2.showAndWait();

        }

    }

    @FXML
    void pressBtnVerify(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnVerify.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root, 900, 530);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
