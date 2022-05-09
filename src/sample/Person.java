package sample;

import java.util.Random;

public class Person {
    private String Firstname;
    private String Lastname;
    private String Birthdate;
    private String Gender;
    private String Country;
    private String ID;
    private String Fathername;
    private String Phonenumber;
    private String Password;
    private String InitialAmount;

    public String getFirstname(){
        return Firstname;
    }
    public void setFirstname(String Firstname){
        this.Firstname=Firstname;
    }

    public void setLastname(String Lastname)
    {
        this.Lastname=Lastname;
    }
    public String getBirthDate()
    {
        return Birthdate;
    }
    public void setBirthDate(String BirthDate)
    {
        this.Birthdate=BirthDate;
    }
    public String getGender() {return Gender;}
    public void setGender(String Gender)
    {
        this.Gender=Gender;
    }
    public String getCountry()
    {
        return Country;
    }
    public void setCountry(String Country)
    {
        this.Country=Country;
    }
    public String getID()
    {
        return ID;
    }
    public void setID(String ID)
    {
        this.ID=ID;
    }
    public String getFathername()
    {
        return Fathername;
    }
    public void setFathername(String Fathername)
    {
        this.Fathername=Fathername;
    }
    public String getPhonenumber()
    {
        return Phonenumber;
    }
    public void setPhonenumber(String Phonenumber)
    {
        this.Phonenumber=Phonenumber;
    }
    public String getPassword()
    {
        return Password;
    }
    public void setPassword(String Password)
    {
        this.Password=Password;
    }
    public String getInitialAmount()
    {
        return InitialAmount;
    }
    public void setInitialAmount(String InitialAmount)
    {
        this.InitialAmount=InitialAmount;
    }

    public String CreateAccountNumber(){
        Random rnd= new Random();
        int AccNum=rnd.nextInt(1000);
        String AccountNumber="6969"+String.valueOf(AccNum);
        return AccountNumber;
    }


    public String getLastname() {
        return Lastname;
    }
}
