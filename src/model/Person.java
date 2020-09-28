package model;

import java.util.ArrayList;
import java.util.Calendar;
import customExceptions.ActionsOnInactiveException;
import customExceptions.AlreadyActiveException;
import customExceptions.AlreadyInactiveException;
import customExceptions.AlreadyPaidException;
import customExceptions.NotEnoughtMoneyException;
import customExceptions.NotFoundCardException;

public class Person implements Comparable<Person>{
	
	public final static int MALE = 1;
	public final static int FEMALE = 0;
	public final static int SENIOR = 60;
	
	private String name;
	private int id;
	private long accountNumber;
	private double totalDebt;//esto representa cuanto debido en la tarjeta de credito
	private boolean activeAccount;
	private double amountAccount;// dinero en la cuenta de ahorros
	private ArrayList<Card> cards;
	private Calendar ingress;
	private int age;
	private boolean invalid;
	private int gender;
	private boolean pregnated;
	private int canUndoActions;
	
	
	/**
	 * @param name
	 * @param id
	 * @param accountNumber
	 * @param cards
	 * @param ingress
	 */
	public Person(String name, int id, long accountNumber, ArrayList<Card> cards, Calendar ingress,
					int age, boolean invalid, int gender, boolean pregnated) {
		super();
		this.name = name;
		this.id = id;
		this.accountNumber = accountNumber;
		this.cards = cards;
		this.ingress = ingress;
		this.age = age;
		this.invalid = invalid;
		this.gender = gender;
		this.pregnated = pregnated;
		canUndoActions = 0;
		activeAccount = true;
	}
	
	public void addCard(long number, int paymentDate, int cvc, int fees, int quota, double owe, double cardSpace) {
		cards.add(new Card(number, paymentDate, cvc, fees, quota, owe, cardSpace));
	}
	
	public boolean searchCardToCreate(long number) {
		boolean equals = false;
		for (int i = 0; i < cards.size() && !equals; i++) {
			if (number == cards.get(i).getNumber()) {
				equals = true;
			}
		}
		return equals;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the invalid
	 */
	public boolean isInvalid() {
		return invalid;
	}

	/**
	 * @param invalid the invalid to set
	 */
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the pregnated
	 */
	public boolean isPregnated() {
		return pregnated;
	}

	/**
	 * @param pregnated the pregnated to set
	 */
	public void setPregnated(boolean pregnated) {
		this.pregnated = pregnated;
	}

	/**
	 * @param totalDebt the totalDebt to set
	 */
	public void setTotalDebt(double totalDebt) {
		this.totalDebt = totalDebt;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * @return the cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * @param cards the cards to set
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * @return the ingress
	 */
	public Calendar getIngress() {
		return ingress;
	}
	
	/**
	 * @param ingress the ingress to set
	 */
	public void setIngress(Calendar ingress) {
		this.ingress = ingress;
	}

	public double getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt() {//de las tarjetas
		double amount = 0;
		for (int i = 0; i < cards.size(); i++) {
			amount += cards.get(i).getOwe();
		}
		totalDebt = amount;
	}


	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the canUndoActions
	 */
	public int getCanUndoActions() {
		return canUndoActions;
	}

	/**
	 * @param canUndoActions the canUndoActions to set
	 */
	public void setCanUndoActions(int canUndoActions) {
		this.canUndoActions = canUndoActions;
	}
	
	
	public boolean canUndo() {
		boolean can = false;
		if (canUndoActions > 0) {
			can = true;
		}
		return can;
	}
	
	/**
	 * @return the amountAccount
	 */
	public double getAmountAccount() {
		return amountAccount;
	}

	/**
	 * @param amountAccount the amountAccount to set
	 */
	public void setAmountAccount(double amountAccount) {
		this.amountAccount = amountAccount;
	}
	
	public void consignment(double more) throws ActionsOnInactiveException {
		if (!activeAccount) {
			throw new ActionsOnInactiveException(name, accountNumber);
		}
		amountAccount = amountAccount + more;
	}
	
	public void withdrawals(double more) throws NotEnoughtMoneyException, ActionsOnInactiveException {
		if (amountAccount >= more) {
			if (!activeAccount) {
				throw new ActionsOnInactiveException(name, accountNumber);
			}
			amountAccount = amountAccount - more;
		} else {
			throw new NotEnoughtMoneyException(amountAccount, more, name, accountNumber);
		}
	}

	/**
	 * @return the activeAccount
	 */
	public boolean isActiveAccount() {
		return activeAccount;
	}

	/**
	 * @param activeAccount the activeAccount to set
	 */
	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}
	
	public void cancelAccount() throws AlreadyInactiveException {
		if (activeAccount) {
			amountAccount = 0;
			activeAccount = false;
		} else {
			throw new AlreadyInactiveException(name, accountNumber);
		}
	}
	
	public void activeAccount() throws AlreadyActiveException {
		if (!activeAccount) {
			activeAccount = true;
		} else {
			throw new AlreadyActiveException(name, accountNumber);
		}
	}
	
	public void payCard(long number, boolean total) throws NotFoundCardException, AlreadyPaidException {
		Card c = searchCard(number);
		if (total) {
			c.payTotal();
		} else {
			c.payNextQuote();
		}
		setTotalDebt();
	}
	
	public Card searchCard(long number) throws NotFoundCardException {
		Card theCard = null;
		boolean found = false;
		for (int i = 0; i < cards.size() && !found; i++) {
			if (cards.get(i).getNumber() == number) {
				theCard = cards.get(i);
				found = true;
			}
		}
		if (!found) {
			throw new NotFoundCardException(name, number);
		}
		return theCard;
	}


}
