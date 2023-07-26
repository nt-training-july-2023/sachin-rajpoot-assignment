package Bank_Que_8;

class BankAccount {
	private double balance;

	public BankAccount(double balance) {
		this.balance = balance;
	}

	public synchronized void deposit(double amount) {
		double newBalance = balance + amount;
		System.out.println(Thread.currentThread().getName() + " deposited " + amount);
		System.out.println(Thread.currentThread().getName() + " updated balance is " + newBalance);
		balance = newBalance;
	}

	public synchronized void withdraw(double amount) {
		double newBalance = balance - amount;
		System.out.println(Thread.currentThread().getName() + " withdraw " + amount);
		System.out.println(Thread.currentThread().getName() + " updated balance is " + newBalance);
		balance = newBalance;
	}

	public void getBalance() {
		System.out.println("current balance is " + balance);
	}
}

class Operations extends Thread {
	BankAccount account;
	private double amount;

	public Operations(BankAccount account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public void run() {

		if (amount > 0) {
			account.deposit(amount);
		} else {
			account.withdraw(Math.abs(amount));
		}
	}

}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankAccount account = new BankAccount(1000);

		Operations op1 = new Operations(account, 100);
		Operations op2 = new Operations(account, -100);
		Operations op3 = new Operations(account, -100);
		Operations op4 = new Operations(account, 300);

		op1.start();
		op2.start();
		op3.start();
		op4.start();

//		try {
//			op1.join(); op2.join(); op3.join(); op4.join();
//		} catch(Exception e ) {
//			e.printStackTrace();
//		}

	}

}
