package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import customExceptions.AreadyAddedCardException;
import customExceptions.AreadyAddedIdException;

public class Person implements Comparable<Person>{
	
	public final static int MALE = 1;
	public final static int FEMALE = 0;
	
	private String name;
	private int id;
	private long accountNumber;
	private ArrayList<Card> cards;
	private Calendar ingress;
	private double totalDebt;
	private int age;
	private boolean invalid;
	private int gender;
	private boolean pregnated;
	
	
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
	}
	
	public void addCard(long number, Calendar paymentDate, int cvc, Calendar dueDate, int fees, int quota, double owe, double cardSpace) throws AreadyAddedCardException {
		for (int i = 0; i < cards.size(); i++) {
			if (number == cards.get(i).getNumber()) {
				throw new AreadyAddedCardException(number, name);
			}
		}
		cards.add(new Card(number, paymentDate, cvc, dueDate, fees, quota, owe, cardSpace));
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
	
	/*
	 * TODO
	 */
}
