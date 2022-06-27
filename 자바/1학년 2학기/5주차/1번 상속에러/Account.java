public class Account {
	private static int lastAccountNumber = 0;
	private int accountNumber, balance; 
	
	Account(int amount) {
		accountNumber = ++lastAccountNumber;
		balance = amount;
	}
	public int getAccountNumber(){ return accountNumber; }
	public int getBalance() { return balance; }
	public void deposit(int amount) { balance += amount; }
	public void withdraw(int amount) {
		if(balance >= amount) balance -= amount;
	}
}

