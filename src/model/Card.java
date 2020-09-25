package model;

import java.util.Date;

public class Card {
	
	
	//al agregar el usuario el ya viene con una deuda en la tarjeta definida, ya que en el transcurso del tiempo no podra aumentar esa deuda-hacer pagos con ella-
	//funcionalidad usar tarjeta???
	
	
	private long number;
	private Date paymentDate;
	private int cvc;
	private String dueDate;
	private int cuotas;
	private int cupo; 
	
	/**
	 * @param number
	 * @param paymentDate
	 * @param cvc
	 * @param dueDate
	 */
	public Card(long number, Date paymentDate, int cvc, String dueDate, int cuotas, int cupo) {
		super();
		this.number = number;
		this.paymentDate = paymentDate;
		this.cvc = cvc;
		this.dueDate = dueDate;
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
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
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
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
}
