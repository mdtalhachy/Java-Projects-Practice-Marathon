package bankAccountApp;

public interface IRate {
	//Write a method that returns base rate
	
	default double getBaseRate() {
		return 2.5;
	}
}
