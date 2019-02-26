
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AccountUtility {
    
    ArrayList<String> accountNumbers;
    LinkedHashMap<String, Account> mapNumAccount;
    
    
    public ArrayList<String> getAccountNumbers(){
        
        accountNumbers = new ArrayList();
        mapNumAccount = new LinkedHashMap();
        
        File accountFile = new File("accounts.txt");
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(accountFile));
            
            String line = in.readLine();
            while(line != null){
                String[] accountArray = line.split("<>");
                accountNumbers.add(accountArray[0]);

                String[] dateArray = accountArray[1].split("/");

                GregorianCalendar openDate = new GregorianCalendar();
                openDate.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));

                CheckingAccount account = new CheckingAccount(accountArray[0], accountArray[2], openDate, Double.valueOf(accountArray[3]));
                mapNumAccount.put(accountArray[0], account);
                
                line = in.readLine();
            }
            in.close();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return this.accountNumbers;
    }
    
    public void save(){
        PrintWriter out = null;
        try {
            File accountsFile = new File("accounts.txt");
            out = new PrintWriter(new BufferedWriter(new FileWriter(accountsFile)));
            
            for(Map.Entry<String, Account> entry : mapNumAccount.entrySet()){
                Account account = entry.getValue();
                
//              Convert openDate to formatted string
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
                fmt.setCalendar(account.openDate);
                String dateFormatted = fmt.format(account.openDate.getTime());
                
                out.println(account.number + "<>" + dateFormatted + "<>" + account.name + "<>" + account.balance);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            out.close();
        }
    }
    
    public LinkedHashMap<String, Account> getMapNumAccount(){
        return mapNumAccount;
    }
    
}
