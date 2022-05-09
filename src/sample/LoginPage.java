package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginPage {

    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;
    static String AccNum;

    @FXML
    private Label lblSignIn;

    @FXML
    private RadioButton btnManager;

    @FXML
    private RadioButton btnClient;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnDone;

    @FXML
    private Label lblNotHaveAccount;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private Label lblWelcome;

    @FXML
    void pressBtnDone(ActionEvent event) throws IOException , SQLException {
        boolean haha=false;
        if(btnManager.isSelected()==false&&btnClient.isSelected()==false||txtUsername.getText().equals("")||txtPassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Compelete all the parts!!");
            alert.showAndWait();
        }else {

            if(btnClient.isSelected())
            {
                connectionForDataBase();
                String sql = "SELECT nc, Password ,AccountNum FROM Clients";
                boolean HAHAHA=false;
                try {
                    ResultSet rs=statementforDataBase.executeQuery(sql);
                    while(rs.next()) {
                        String username = rs.getString("nc");
                        String password = rs.getString("Password");

                        if (txtUsername.getText().compareTo(username) == 0 && txtPassword.getText().compareTo(password) == 0) {
                            AccNum=rs.getString("AccountNum");
                            HAHAHA = true;
                            Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                            alert5.setTitle("Hey You");
                            alert5.setContentText("Welcome!");
                            alert5.showAndWait();
                            Stage stage = (Stage) btnDone.getScene().getWindow();
                            stage.close();
                            Stage primaryStage = new Stage();
                            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Main-Transactions.fxml"));
                            Scene scene = new Scene(root, 800, 500);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            break;
                        }}
                    if (HAHAHA == false) {
                        Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                        alert6.setTitle("Hey You");
                        alert6.setContentText("Username or Password is wrong!");
                        alert6.showAndWait();
                        txtUsername.setText("");
                        txtPassword.setText("");
                    }

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(btnManager.isSelected())
            {
                if(txtUsername.getText().equals("ShadiSariSarraf")&& txtPassword.getText().equals("123"))
                {
                    haha=true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hey You");
                    alert.setContentText("Welcome!");
                    alert.showAndWait();
                    Stage stage =(Stage) btnDone.getScene().getWindow();
                    stage.close();
                    Stage primaryStage=new Stage();
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
                    Scene scene = new Scene(root,600,700);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                if(haha==false){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Hey You");
                    alert2.setContentText("Username or Password is wrong!");
                    alert2.showAndWait();
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        }
        connectionforDataBase.close();
        }


    @FXML
    void pressBtnSignUp(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnSignUp.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
        Scene scene = new Scene(root,600,750);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void connectionForDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase =  connectionforDataBase.createStatement();
            System.out.println("Connection Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getAccNum(){
        return AccNum;
    }

}
