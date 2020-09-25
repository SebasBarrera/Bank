package model;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	
	public String name;
	public int id;
	public long accountNumber;
	public ArrayList<Card> cards;
	public Date ingress;
	private double totalDebt;
	
	/**
	 * @param name
	 * @param id
	 * @param accountNumber
	 * @param cards
	 * @param ingress
	 */
	public Person(String name, int id, long accountNumber, ArrayList<Card> cards, Date ingress) {
		super();
		this.name = name;
		this.id = id;
		this.accountNumber = accountNumber;
		this.cards = cards;
		this.ingress = ingress;
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
	public Date getIngress() {
		return ingress;
	}
	
	/**
	 * @param ingress the ingress to set
	 */
	public void setIngress(Date ingress) {
		this.ingress = ingress;
	}

	public double getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt() {
		double amount = 0;
		for (int i = 0; i < cards.size(); i++) {
			amount += cards.get(i).getDebt();
		}
		totalDebt = amount;
	}
	
	/*
	 * TODO
	 */
}
