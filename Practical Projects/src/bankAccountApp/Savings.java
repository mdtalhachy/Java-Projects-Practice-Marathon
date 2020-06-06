package bankAccountApp;

public class Savings extends Account {
	//List properties specific to the Savings account
	private int safetyDepositBoxID;
	private int safetyDepositBoxKey;
	
	//Constructor to initialize settings for the Savings properties
	public Savings(String name, String ssn, double initDeposit) {
		super(name, ssn, initDeposit);
		accountNumber = "1" + accountNumber;
		
		setSafetyDepositBox();
	}
	
	@Override
	public void setRate() {
		rate = getBaseRate() - .25;
	}
	
	public void setSafetyDepositBox() {
		safetyDepositBoxID = (int) (Math.random() * Math.pow(10, 3));
		safetyDepositBoxKey = (int) (Math.random() * Math.pow(10, 4));
	}
	
	//List any methods specific to the Savings account 
	public void showInfo() {
		System.out.println("Account Type: Saving");
		super.showInfo();
		System.out.println("Your Savings Account Features"
				+ "\nSafety Deposit Box ID: " + safetyDepositBoxID
				+ "\nSafety Deposit Box Key: " + safetyDepositBoxKey);
	}
}
