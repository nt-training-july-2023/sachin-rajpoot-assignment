/**
 * This program implements a ATM machine
 * which asks for account balance and widthdrawl
 * amount and looks for any invalid input
 * 
 * 
 * @author Sachin
 * @version 1.0
 * @since 24-07-2023
 * 
 */

package ATM_Que_2;

import java.util.Scanner;

class InvalidInputException extends Exception {
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}

class Account {

	final private int AccountNumber = 123;

	private int AccountBalance;

	/**
	 * This method will display account number
	 */
	void getAccountNumber() {
		System.out.println("Account Number is : " + AccountNumber);
	}

	/**
	 * This method is used to set the value of account balance
	 * 
	 * @param amount is the parameter which is used to set account balance
	 * @throws InvalidInputException
	 */
	void setAccountBalance(int amount) throws InvalidInputException {

		if (amount <= 0) {
			throw new InvalidInputException("Enter Valid Amount balance");
		}
		this.AccountBalance = amount;
		System.out.println("Account balance is : " + AccountBalance);

	}

	/**
	 * This method will display the account balance
	 */
	void getAccountBalance() {
		System.out.println("Account balance is : " + AccountBalance);
	}

	/**
	 * This method is used to withdraw from account
	 * 
	 * @param amount is the parameter which we want to withdraw
	 * @throws InvalidInputException
	 */
	void withdrawl(int amount) throws InvalidInputException {
		if (amount > AccountBalance || amount <= 0) {
			throw new InvalidInputException("Enter Valid withdrawl Amount");
		} else {
			AccountBalance -= amount;
			System.out.println("Withdraw successfull");
			getAccountBalance();
		}
	}

	/**
	 * This method is used to deposit amount into account
	 * 
	 * @param amount is the money we want to add in account
	 * @throws InvalidInputException
	 */
	void deposit(int amount) throws InvalidInputException {
		if (amount <= 0) {
			throw new InvalidInputException("Enter Valid deposit Amount");
		} else {
			AccountBalance += amount;
			System.out.println("Deposit successfull");
			getAccountBalance();
		}
	}

	public class Main {
		/**
		 * This is the main method which makes use of account class functionalities.
		 * 
		 * @throws InvalidInputException
		 */
		public static void main(String[] args) throws InvalidInputException {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Account Balance and withdrawl amount : ");

			Account ac = new Account();

			int accountBalance = 0, withdrawAmmount = 0;

			String s1 = sc.next();
			String s2 = sc.next();
			try {
				accountBalance = Integer.parseInt(s1);
				withdrawAmmount = Integer.parseInt(s2);
			} catch (Exception e) {
				throw new InvalidInputException("Enter Valid balance and withdrawl Amount");
			}

			ac.setAccountBalance(accountBalance);
			ac.withdrawl(withdrawAmmount);

		}

	}
}
