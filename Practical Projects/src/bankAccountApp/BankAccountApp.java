package bankAccountApp;

import java.util.ArrayList;
import java.util.List;

public class BankAccountApp {
	public static void main(String args[]) {
		
		List<Account> accounts = new ArrayList<Account>();		
		
		//Read a CSV file and create account based on the data
		String file = "G:\\Java Workplace 2020\\original.csv";
		List<String[]> newAccountHolders = utilities.CSV.read(file);
		
		for(String[] accountHolder: newAccountHolders) {
			String name = accountHolder[0];
			String ssn = accountHolder[1];
			String accountType = accountHolder[2];
			double initDeposit = Double.parseDouble(accountHolder[3]);
			
			//System.out.println(name + " " + ssn + " " + accountType + " " + initDeposit);
			
			if(accountType.equals("Savings")) {
				accounts.add(new Savings(name, ssn, initDeposit));
			}else if(accountType.equals("Checking")) {
				accounts.add(new Checking(name, ssn, initDeposit));
			}else {
				System.out.println("Error Reading Account Type");
			}
		}
		

		for(Account acc: accounts) {
			System.out.println("\n**************");
			acc.showInfo();
		}
		/*
		Checking chkAccount1 = new Checking("Tom Cat", "562369589", 1500);
		
		Savings saveAccount1 = new Savings("Rob Lowe", "123654897", 2500);
		
		saveAccount1.compound();
		
		saveAccount1.showInfo();
		chkAccount1.showInfo();
		*/
		
		
		
		
	}

}
