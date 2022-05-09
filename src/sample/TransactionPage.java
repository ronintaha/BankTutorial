package sample;

public class TransactionPage {
    private String SrcAccNum;
    private String DesAccNum;
    private String Amount;
    private String Type;

    public String getSrcAccNum(){
        return SrcAccNum;
    }
    public void setSrcAccNum(String SrcAccNum){
        this.SrcAccNum=SrcAccNum;
    }


    public String getDesAccNum(){
        return DesAccNum;
    }
    public void setDesAccNum(String DesAccNum){
        this.DesAccNum=DesAccNum;
    }


    public String getAmount(){
        return Amount;
    }
    public void setAmount(String Amount){
        this.Amount=Amount;
    }


    public String getType(){
        return Type;
    }
    public void setType(String Type){
        this.Type=Type;
    }
}

