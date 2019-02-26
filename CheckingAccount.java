
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;


public class CheckingAccount extends Account {
    
    public CheckingAccount(String number, String name, GregorianCalendar openDate, double balance){
        super(number, name, openDate, balance);
    }
            
    @Override
    public int transferTo(Account account, double transferAmount) {
        
        if(this.balance < AccountConstants.CHECKING_BALANCE_THRESHOLD){
        // Add transfer fee
            if(this.balance >= transferAmount + AccountConstants.TRANSFER_FEE){
                this.withdraw(transferAmount + AccountConstants.TRANSFER_FEE);
                account.deposit(transferAmount);

                return 1;
            }
            else{
                return -1;
            }
        }
        else {
        //  No transfer fee
            if(this.balance >= transferAmount){
                this.withdraw(transferAmount);
                account.deposit(transferAmount);

                return 0;
            }
            else{
                return -2;
            }
        }
    }
}
