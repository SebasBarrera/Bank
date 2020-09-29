package model;

import customExceptions.AlreadyPaidException;

public class Card {
	
	private long number;
	private int paymentDay; // indica el limite dia del mes en el cual pagara la tarjeta
	private int cvc;
	private int fees; // interes periodico mensual vencido
	private int quota; // nº cuotas
	private double aliquot; //valor de la alicuota
	private double owe;// cuanto debe de la tarjeta
	private double cardSpace; // cupo de la tarjeta
	private boolean isPaid; //indica si ya fue pagada en su totalidad o no
	
	/**
	 * @param number
	 * @param paymentDay
	 * @param cvc
	 * @param dueDate
	 */
	public Card(long number, int paymentDay, int cvc, int fees, int quota, double owe, double cardSpace) {
		super();
		this.number = number;
		this.paymentDay = paymentDay;
		this.cvc = cvc;
		this.fees = fees;
		this.quota = quota;
		this.owe = owe;
		this.cardSpace = cardSpace;
		calculateAliquot();
		calculateTotalOwe();
		isPaid = false;
	}
	
	private void calculateAliquot() {
		aliquot = (owe * Math.pow((1 + fees), quota) * fees) / (Math.pow((1 + fees), quota) - 1); //formula de la alicuota
		
	}

	public void calculateTotalOwe() {
		owe = aliquot * quota; //valor total que se debera
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
	 * @return the paymentDay
	 */
	public int getPaymentDay() {
		return paymentDay;
	}

	/**
	 * @param paymentDay the paymentDay to set
	 */
	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
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
	
	
	public void payTotal() throws AlreadyPaidException {
		if (!isPaid) {
			isPaid = true;
			owe = 0;
		} else {
			throw new AlreadyPaidException(number);
		}
	}
	
	public void payNextQuote() throws AlreadyPaidException {
		if (!isPaid) {
			owe = owe - aliquot;
			if (owe == 0) {	
				isPaid = true;
			}
		} else {
			throw new AlreadyPaidException(number);
		}
	}

	/**
	 * @return the aliquot
	 */
	public double getAliquot() {
		return aliquot;
	}

	/**
	 * @param aliquot the aliquot to set
	 */
	public void setAliquot(double aliquot) {
		this.aliquot = aliquot;
	}

	/**
	 * @return the isPaid
	 */
	public boolean isPaid() {
		return isPaid;
	}

	/**
	 * @param isPaid the isPaid to set
	 */
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
}
