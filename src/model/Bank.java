package model;

import java.util.ArrayList;
import java.util.Calendar;
import customExceptions.ActionsOnInactiveException;
import customExceptions.AlreadyActiveException;
import customExceptions.AlreadyInactiveException;
import customExceptions.AlreadyPaidException;
import customExceptions.AreadyAddedIdException;
import customExceptions.NotEnoughtMoneyException;
import customExceptions.NotFoundCardException;
import customExceptions.NothingToRedoException;
import customExceptions.NothingToUndoException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;
import dataStructure.*;


public class Bank {
	
	private ArrayList<Person> persons;
	private Heap<Person> priorityRow;
	private Queue<Person> normalRow;
	private HashTable<Integer, Person> dataBase;
	private Stack<Person> undo;
	private Stack<Person> redo;
	
	public Bank() {
		persons = new ArrayList<>();
		priorityRow = new Heap<>(0, 0);
		normalRow = new Queue<>();
		dataBase = new HashTable<>();
		undo = new Stack<>();
		redo = new Stack<>();
	}
	
	public void addPerson(String name, int id, ArrayList<Card> cards, Calendar ing, 
			int age,boolean invalid, int gender, boolean pregnated) throws AreadyAddedIdException {
		for (int i = 0; i < persons.size(); i++) {
			if (id == persons.get(i).getId()) {
				throw new AreadyAddedIdException(id, persons.get(i).getName());
			}
		}
		long ac = newAccountNumber();
		persons.add(new Person(name, id, ac, cards, ing, age, invalid, gender, pregnated));
	}
	
	
	public void addPersonToRow(int id, String name) throws SmallerKeyException, UserIsNotRegiterException {
		int priority = 0;
		Person p = searchHash(id, name);//busqyeda efectiva... hashtable?
		if (p.getAge() >= Person.SENIOR) {
			priority++;
		}
		if (p.isInvalid()) {
			priority++;
		}
		if (p.getGender() == Person.FEMALE) {
			if (p.isPregnated()) {
				priority++;
			}
		}
		if (priority == 0) {
			normalRow.offer(p);
		} else {
			if (priorityRow.getArraysize() == 0) {
				Person[] ps = new Person[1];
				ps[0] = p;
				priorityRow.setElements(ps);
				int[] prio = new int[1];
				prio[0] = priority;
			} else {
				priorityRow.priorityInsert(priority, p);
			}
			
		}		
	}

	public Person searchHash(int id, String name) throws UserIsNotRegiterException {
		Person p = dataBase.search(id);
		if (p == null) {
			throw new UserIsNotRegiterException(id, name);
		}
		return p;
	}
	
	public void consignment(Person p, int value) throws ActionsOnInactiveException {
		undo.push(p);
		p.consignment(value);
	}
	
	public void withdrawals(Person p, int value) throws NotEnoughtMoneyException, ActionsOnInactiveException {
		undo.push(p);
		p.withdrawals(value);
	}

	public void cancelAccount(Person p) throws AlreadyInactiveException {
		undo.push(p);
		p.cancelAccount();
	}
	
	public void activeAccount(Person p) throws AlreadyActiveException {
		undo.push(p);
		p.activeAccount();
	}
	
	public void payCard(Person p, long number, boolean total) throws NotFoundCardException, AlreadyPaidException {// si total es true paga toda la tarjeta, si no paga una cuota
		undo.push(p);
		p.payCard(number, total);
	}
	
	public void addCard(Person p, int paymentDay, int fees, int quotas, double owe, double cardSpace) {
		long number = newCardNumber();
		int cvc = newCVC();
		p.addCard(number, paymentDay, cvc, fees, quotas, owe, cardSpace);
	}
	
	private int newCVC() {
		int cvc;
		cvc = (int) Math.random() * 900 + 100;
		return cvc;
	}

	public long newCardNumber() {
		double number;
		number = Math.random() * (Integer.MAX_VALUE - Integer.MAX_VALUE-10000000) + Integer.MAX_VALUE-10000000;
		number = number * 1000000000;
		boolean equals = false;
		for (int i = 0; i < persons.size() && !equals; i++) {
			equals = persons.get(i).searchCardToCreate((long) number);
		}
		if (equals) {
			newCardNumber();
		}
		return (long) number;
	}
	
	public long newAccountNumber() {
		double number;
		number = Math.random() * (Integer.MAX_VALUE - Integer.MAX_VALUE-10000000) + Integer.MAX_VALUE-10000000;
		number = number * 1000000000;
		boolean equals = false;
		for (int i = 0; i < persons.size() && !equals; i++) {
			if (persons.get(i).getAccountNumber() == number) {
				equals = true;
			}
		}
		if (equals) {
			newCardNumber();
		}
		return (long) number;
	}
	
	public void undo(Person p) throws NothingToUndoException {
		if (!undo.isEmpty()) {
			redo.push(undo.top());
			p = undo.pop();
		} else {
			throw new NothingToUndoException(p.getName());
		}
	}
	
	public void redo(Person p) throws NothingToRedoException {
		if (!redo.isEmpty()) {
			undo.push(redo.top());
			p = undo.pop();
		} else {
			throw new NothingToRedoException(p.getName());
		}
	}
	
}
