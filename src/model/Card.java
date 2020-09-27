package model;

import java.util.Calendar;

public class Card {
	
	
	//al agregar el usuario el ya viene con una deuda en la tarjeta definida, ya que en el transcurso del tiempo no podra aumentar esa deuda-hacer pagos con ella-
	//funcionalidad usar tarjeta???
	
	
	private long number;
	private Calendar paymentDate;
	private int cvc;
	private Calendar dueDate;
	private int fees;
	private int quota; 
	private double owe;
	private double cardSpace;
	
	/**
	 * @param number
	 * @param paymentDate
	 * @param cvc
	 * @param dueDate
	 */
	public Card(long number, Calendar paymentDate, int cvc, Calendar dueDate, int fees, int quota, double owe, double cardSpace) {
		super();
		this.number = number;
		this.paymentDate = paymentDate;
		this.cvc = cvc;
		this.dueDate = dueDate;
		this.fees = fees;
		this.quota = quota;
		this.owe = owe;
		this.cardSpace = cardSpace;
	}

	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * @return the paymentDate
	 */
	public Calendar getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the cvc
	 */
	public int getCvc() {
		return cvc;
	}

	/**
	 * @param cvc the cvc to set
	 */
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	/**
	 * @return the dueDate
	 */
	public Calendar getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public double getOwe() {
		return owe;
	}

	public void setOwe(double owe) {
		this.owe = owe;
	}

	/**
	 * @return the cardSpace
	 */
	public double getCardSpace() {
		return cardSpace;
	}

	/**
	 * @param cardSpace the cardSpace to set
	 */
	public void setCardSpace(double cardSpace) {
		this.cardSpace = cardSpace;
	}
	
	
	
}
