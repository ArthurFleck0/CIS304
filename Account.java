
import java.util.GregorianCalendar;


public abstract class Account {
    
    String number;
    String name;
    GregorianCalendar openDate;
    double balance;
    
    public Account(String number, String name, GregorianCalendar openDate, double balance){
        this.number = number;
        this.name = name;
        this.openDate = openDate;
        this.balance = balance;
    }
    
    public void deposit(double deposit){
        balance = balance + deposit;
    }
    
    public void withdraw(double withdraw){
        balance = balance - withdraw;
    }
    
    public abstract int transferTo(Account account, double value);
    
}
