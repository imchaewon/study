package com.example.java_._codingTest.yogiyo;

public class T3 {
	enum StateInfo {
		LOGGED_IN, LOGGED_OUT, SUSPENDED, ERROR
	}

	interface BankAccountState {
		public StateInfo login(String password);
		public StateInfo logout();
		public StateInfo unlock(int resetCode);
		public StateInfo withdrawMoney(int amount);
	}

	class BankAccount {
		private BankAccountState loggedIn;
		private BankAccountState loggedOut;
		private BankAccountState suspended;
		private BankAccountState bankAccountState;
		private int cashBalance;
		private String password;
		private int passwordRetries;
		private int resetCode;

		public BankAccount(int cashBalance, String password, int resetCode) {
			this.cashBalance = cashBalance;
			this.password = password;
			this.resetCode = resetCode;
			this.passwordRetries = 0;

			this.loggedIn = new LoggedIn(this);
			this.loggedOut = new LoggedOut(this);
			this.suspended = new Suspended(this);

			this.bankAccountState = this.loggedOut;
		}

		public void setState(BankAccountState state) {
			this.bankAccountState = state;
		}

		public BankAccountState getState() {
			return this.bankAccountState;
		}

		public BankAccountState getLoggedInState() {
			return this.loggedIn;
		}

		public BankAccountState getLoggedOutState() {
			return this.loggedOut;
		}

		public BankAccountState getSuspendedState() {
			return this.suspended;
		}

		public StateInfo login(String password) {
			return this.bankAccountState.login(password);
		}

		public StateInfo logout() {
			return this.bankAccountState.logout();
		}

		public StateInfo unlock(int resetCode) {
			return this.bankAccountState.unlock(resetCode);
		}

		public StateInfo withdrawMoney(int amount) {
			return this.bankAccountState.withdrawMoney(amount);
		}

		public void setCashBalance(int amount) {
			this.cashBalance = amount;
		}

		public int getCashBalance() {
			return this.cashBalance;
		}

		public String getPassword() {
			return this.password;
		}

		public void setPasswordRetries(int passwordRetries) {
			this.passwordRetries = passwordRetries;
		}

		public int getPasswordRetries() {
			return this.passwordRetries;
		}

		public int getResetCode() {
			return this.resetCode;
		}
	}

	class LoggedIn implements BankAccountState {
		private BankAccount bankAccount;
		public LoggedIn(BankAccount bankAccount) { this.bankAccount = bankAccount; }

		public StateInfo login(String password) { return StateInfo.LOGGED_IN; }
		public StateInfo logout() {
			bankAccount.setState(bankAccount.getLoggedOutState());
			return StateInfo.LOGGED_OUT;
		}
		public StateInfo unlock(int resetCode) { return StateInfo.LOGGED_IN; }
		public StateInfo withdrawMoney(int amount) {
			if (amount > bankAccount.getCashBalance()) return StateInfo.LOGGED_IN;
			bankAccount.setCashBalance(bankAccount.getCashBalance() - amount);
			return StateInfo.LOGGED_IN;
		}
	}

	class LoggedOut implements BankAccountState {
		private BankAccount bankAccount;
		public LoggedOut(BankAccount bankAccount) { this.bankAccount = bankAccount; }

		public StateInfo login(String password) {
			if (password.equals(bankAccount.getPassword())) {
				bankAccount.setState(bankAccount.getLoggedInState());
				return StateInfo.LOGGED_IN;
			}
			bankAccount.setPasswordRetries(bankAccount.getPasswordRetries() + 1);
			if (bankAccount.getPasswordRetries() > 2) {
				bankAccount.setState(bankAccount.getSuspendedState());
				return StateInfo.SUSPENDED;
			}
			return StateInfo.LOGGED_OUT;
		}
		public StateInfo logout() { return StateInfo.LOGGED_OUT; }
		public StateInfo unlock(int resetCode) { return StateInfo.LOGGED_OUT; }
		public StateInfo withdrawMoney(int amount) { return StateInfo.LOGGED_OUT; }
	}

	class Suspended implements BankAccountState {
		private BankAccount bankAccount;
		public Suspended(BankAccount bankAccount) { this.bankAccount = bankAccount; }

		public StateInfo login(String password) { return StateInfo.SUSPENDED; }
		public StateInfo logout() { return StateInfo.SUSPENDED; }
		public StateInfo unlock(int resetCode) {
			if (resetCode == bankAccount.getResetCode()) {
				bankAccount.setPasswordRetries(0);
				bankAccount.setState(bankAccount.getLoggedOutState());
				return StateInfo.LOGGED_OUT;
			}
			return StateInfo.SUSPENDED;
		}
		public StateInfo withdrawMoney(int amount) { return StateInfo.SUSPENDED; }
	}
}