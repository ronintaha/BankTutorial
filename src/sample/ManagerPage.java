package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class ManagerPage {

    @FXML
    private Label lblWelcomeManager;

    @FXML
    private JFXButton btnClientInfo;

    @FXML
    private JFXButton btnAllTransactions;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXListView<String> LVClientInfo;

    @FXML
    private JFXListView<String> LVAllTransactions;

    final ObservableList<String>ClientInfo= FXCollections.observableArrayList();
    final ObservableList<String>AllTransactions= FXCollections.observableArrayList();


    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;


    private static void connectionforDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase = connectionforDataBase.createStatement();
            System.out.println("Connection Created db");
        } catch (Exception e) {
            System.out.println(e.getMessage()); }}






    @FXML
    void pressBtnAllTransactions(ActionEvent event) throws SQLException {
        LVClientInfo.setVisible(false);
        LVAllTransactions.setVisible(true);
        connectionforDataBase();
        String sql="SELECT SourceAccountNum,DesAccountNum,Amount,Type FROM Transactions";
        ResultSet res= statementforDataBase.executeQuery(sql);
        while (res.next()) {
            String SourceAccountNum=res.getString("SourceAccountNum");
            String DesAccountNum=res.getString("DesAccountNum");
            String Amount=res.getString("Amount");
            String Type=res.getString("Type");

                AllTransactions.add(SourceAccountNum+"   "+DesAccountNum+"   "+Amount+"   "+Type);
        }


        LVAllTransactions.setItems(AllTransactions);

    }

    @FXML
    void pressBtnClientInfo(ActionEvent event) throws SQLException {
        LVClientInfo.setVisible(true);
        LVAllTransactions.setVisible(false);
        connectionforDataBase();
        String sql="SELECT Firstname,Lastname,Birthdate,Gender,Country,nc,FatherName ,PhoneNumber,Password,Balance,AccountNum FROM Clients";
        ResultSet res=statementforDataBase.executeQuery(sql);
        while(res.next()){
            String Firstname=res.getString("Firstname");
            String Lastname=res.getString("Lastname");
            String Birthdate=res.getString("Birthdate");
            String Gender=res.getString("Gender");
            String Country=res.getString("Country");
            String nc=res.getString("nc");
            String FatherName=res.getString("FatherName");
            String PhoneNumber=res.getString("PhoneNumber");
            String Password=res.getString("Password");
            String Balance=res.getString("Balance");
            String AccountNum=res.getString("AccountNum");

            ClientInfo.add(Firstname+"  "+Lastname+"  "+Birthdate+"  "+Gender+"  "+Country+"  "+nc+"  "+FatherName+"  "+PhoneNumber+"  "+Password+"  "+Balance+"  "+AccountNum);

        }
        LVClientInfo.setItems(ClientInfo);



    }

    @FXML
    void pressBtnExit(ActionEvent event) {
        Platform.exit();

    }

}
