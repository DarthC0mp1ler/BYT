package b_Money;

import java.util.Hashtable;

public class Account {
	private Money content;
	private Hashtable<String, TimedPayment> timedpayments = new Hashtable<String, TimedPayment>();
	private String name; //created the field name

	/**
	 * New account
	 * @param name The name of this account
	 * @param currency The currency that is used on this account
	 */
	Account(String name, Currency currency) {
		this.name = name; // constructor received the name but did not save it
		this.content = new Money(0, currency);
	}

	/**
	 * Add a timed payment
	 * @param id Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param tobank Bank where receiving account resides
	 * @param toaccount Id of receiving account
	 */
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) {
		TimedPayment tp = new TimedPayment(interval, next, amount, this, tobank, toaccount);
		timedpayments.put(id, tp);
	}
	
	/**
	 * Remove a timed payment
	 * @param id Id of timed payment to remove
	 */
	public void removeTimedPayment(String id) {
		timedpayments.remove(id);
	}
	
	/**
	 * Check if a timed payment exists
	 * @param id Id of timed payment to check for
	 */
	public boolean timedPaymentExists(String id) {
		return timedpayments.containsKey(id);
	}

	/**
	 * A time unit passes in the system
	 */
	public void tick() {
		for (TimedPayment tp : timedpayments.values()) {
			tp.tick();  //removed one invocation of tick method
						//double tick resulted in to fast performing of timed payment
		}
	}
	
	/**
	 * Deposit money to the account
	 * @param money Money to deposit.
	 */
	public void deposit(Money money) {
		content = content.add(money);
	}
	
	/**
	 * Withdraw money from the account
	 * @param money Money to withdraw.
	 */
	public void withdraw(Money money) {
		content = content.sub(money);
	}

	/**
	 * Get balance of account
	 * @return Amount of Money currently on account
	 */
	public Money getBalance() {
		return content;
	}

	/* Everything below belongs to the private inner class, TimedPayment */
	private class TimedPayment {
		private int interval, next;
		private Account fromaccount;
		private Money amount;
		private Bank tobank;
		private String toaccount;

		/**
		 * New Timed payment
		 * @param interval The interval in ticks when the payment should be performed
		 * @param next The interval in ticks from the creation of the object to the first payment
		 * @param amount The amount of money to be payed
		 * @param fromaccount The account from which the money should be withdrawn
		 * @param tobank The bank to which the payment will be transferred
		 * @param toaccount  The account to which the money will be transferred
		 */
		TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount) {
			this.interval = interval;
			this.next = next;
			this.amount = amount;
			this.fromaccount = fromaccount;
			this.tobank = tobank;
			this.toaccount = toaccount;
		}

		/**
		 * Performs one tick on the instance of TimedPayment
		 * time till the next payment is decremented
		 * if it is 0, then the specified payment is completed and the counter is again set to the interval value
		 * @return a value that indicates whether the payment was performed
		 */
		public Boolean tick() {
			if (next == 0) {
				next = interval;

				fromaccount.withdraw(amount);
				try {
					tobank.deposit(toaccount, amount);
				}
				catch (AccountDoesNotExistException e) {
					/* Revert transfer.
					 * In reality, this should probably cause a notification somewhere. */
					fromaccount.deposit(amount);
				}
				return true;
			}
			else {
				next--;
				return false;
			}
		}
	}

}
