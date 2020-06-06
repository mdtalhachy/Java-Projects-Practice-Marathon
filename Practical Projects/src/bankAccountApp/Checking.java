package bankAccountApp;

public class Checking extends Account{
	//List properties specific to a Checking account
	private int debitCardNumber;
	private int debitCardPIN;
	
	//Constructor to initialize chekcing account properties
	public Checking(String name, String ssn, double initDeposit) {
		super(name, ssn, initDeposit);
		accountNumber = "2" + accountNumber;
		
		setDebitCard();
	}
	
	public void setDebitCard() {
		debitCardNumber = (int) (Math.random() * Math.pow(10, 12));
		debitCardPIN = (int) (Math.random() * Math.pow(10, 4));
	}
	
	@Override
	public void setRate() {
		rate = getBaseRate() * .15;
	}
	
	//List any methods specific to the checking account
	public void showInfo() {
		System.out.println("Account Type: Checking");
		super.showInfo();
		System.out.println("Your Chekcing Account Features: " +
		"\nDebit Card Number: " + debitCardNumber +
		"\nDebit Card PIN: " + debitCardPIN
		);
	}

}
