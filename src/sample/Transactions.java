package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class Transactions {

    @FXML
    private Label lblAccountNum;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXTextField txtDepositAmount;

    @FXML
    private Label lblDeposit;

    @FXML
    private JFXButton btnDeposit;

    @FXML
    private Label lblWithdraw;

    @FXML
    private JFXTextField txtWithdrawAmount;

    @FXML
    private JFXButton btnWithdraw;

    @FXML
    private Label lblTransfer;

    @FXML
    private JFXTextField txtDesAccNum;

    @FXML
    private JFXTextField txtTransferAmount;

    @FXML
    private JFXButton btnTransfer;

    @FXML
    private JFXButton btnTransactions;

    @FXML
    private JFXListView<String> LVTransactions;
    final ObservableList<String>PersonalTransactions= FXCollections.observableArrayList();
    static String AccNum=LoginPage.getAccNum();


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
    void pressBtnDeposit(ActionEvent event) throws SQLException {
        if(txtDepositAmount.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Compelete all the parts!!");
            alert.showAndWait();
        }else{
            TransactionPage trp= new TransactionPage();
            DataBase db= new DataBase();
            trp.setSrcAccNum(AccNum);
            trp.setDesAccNum("");
            trp.setAmount(txtDepositAmount.getText());
            trp.setType("Deposit");
            db.insertAllTransactions(trp);
            String depositAmount=txtDepositAmount.getText();
            long DA=Long.parseLong(depositAmount);
            long currentBalance=Long.parseLong(getBalance(AccNum));
            long updatedBalance=DA+currentBalance;
            updateBalance(updatedBalance,AccNum);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deposited!!");
            alert.showAndWait();

        }

    }

    @FXML
    void pressBtnExit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void pressBtnTransactions(ActionEvent event) throws SQLException{
      connectionforDataBase();
      String sql="SELECT SourceAccountNum,DesAccountNum,Amount,Type FROM Transactions";
      ResultSet res= statementforDataBase.executeQuery(sql);
        while (res.next()) {
            String SourceAccountNum=res.getString("SourceAccountNum");
            String DesAccountNum=res.getString("DesAccountNum");
            String Amount=res.getString("Amount");
            String Type=res.getString("Type");
            if(SourceAccountNum.equals(AccNum)){
            PersonalTransactions.add(SourceAccountNum+"   "+DesAccountNum+"   "+Amount+"   "+Type);}

        }
        LVTransactions.setItems(PersonalTransactions);
    }

    @FXML
    void pressBtnTransfer(ActionEvent event) throws SQLException {

        if(txtDesAccNum.getText().compareTo("")==0||txtTransferAmount.getText().compareTo("")==0){
            Alert alert5 = new Alert(Alert.AlertType.WARNING);
            alert5.setTitle("Warning Dialog");
            alert5.setHeaderText(null);
            alert5.setContentText("Compelete all the parts!!");
            alert5.showAndWait();
        }else{
            long SrcBal=Long.parseLong(getBalance(AccNum));
            String desAccNum=txtDesAccNum.getText();
            long DesBal=Long.parseLong(getBalance(desAccNum));
            long TransferAmount=Long.parseLong(txtTransferAmount.getText());
            if(SrcBal>=TransferAmount){
                TransactionPage trans=new TransactionPage();
                DataBase dbs=new DataBase();
                trans.setSrcAccNum(AccNum);
                trans.setDesAccNum(txtDesAccNum.getText());
                trans.setAmount(txtTransferAmount.getText());
                trans.setType("Transfer");
                dbs.insertAllTransactions(trans);
                long updatedSrcBalance=SrcBal-TransferAmount;
                long updatedDesBalance=DesBal+TransferAmount;
                updateBalance(updatedSrcBalance,AccNum);
                updateBalance(updatedDesBalance, desAccNum);
                Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                alert6.setTitle("Information Dialog");
                alert6.setHeaderText(null);
                alert6.setContentText("Successfully Transfered!!");
                alert6.showAndWait();
            }else{
                Alert alert7 = new Alert(Alert.AlertType.WARNING);
                alert7.setTitle("Warning Dialog");
                alert7.setHeaderText(null);
                alert7.setContentText("Your balance is noy enough to transfer this amount of money!!");
                alert7.showAndWait();
            }
        }

    }

    @FXML
    void pressBtnWithdraw(ActionEvent event) throws SQLException {
        if(txtWithdrawAmount.getText().compareTo("")==0){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Warning Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Compelete all the parts!!");
            alert2.showAndWait();

        }else{
            String WithdrawAmount=txtWithdrawAmount.getText();
            long WA=Long.parseLong(WithdrawAmount);
            long currentBalance=Long.parseLong(getBalance(AccNum));
            if(currentBalance>=WA){
                TransactionPage trp2=new TransactionPage();
                DataBase db2=new DataBase();
                trp2.setSrcAccNum(AccNum);
                trp2.setDesAccNum("");
                trp2.setAmount(txtWithdrawAmount.getText());
                trp2.setType("Withdraw");
                db2.insertAllTransactions(trp2);
                long updatedBalance=currentBalance-WA;
                updateBalance(updatedBalance,AccNum);
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Information Dialog");
                alert3.setHeaderText(null);
                alert3.setContentText("Successfully Withdrawed!!");
                alert3.showAndWait();
            }else{
                Alert alert4 = new Alert(Alert.AlertType.WARNING);
                alert4.setTitle("Warning Dialog");
                alert4.setHeaderText(null);
                alert4.setContentText("Your balance is noy enough to withdraw this amount of money!!");
                alert4.showAndWait();
            }

        }


    }

    public String getBalance(String Accnum) throws SQLException{
        connectionforDataBase();
        String sql="SELECT AccountNum , Balance FROM Clients";
        String Balance="";
        ResultSet RS= statementforDataBase.executeQuery(sql);
        while(RS.next()){
            String DBAccountNum=RS.getString("AccountNum");
            if(DBAccountNum.equals(Accnum)){
                Balance=RS.getString("Balance");
            }
        }
        return Balance;
    }

    public void initialize(){
        lblAccountNum.setText(LoginPage.AccNum);
    }

    public void updateBalance(long newBalance , String accNum) throws SQLException{
        connectionforDataBase();
        String update="UPDATE Clients set Balance='"+newBalance+"'where AccountNum='"+accNum+"';";
        statementforDataBase.executeUpdate(update);

    }

}
