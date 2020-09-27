package model;

import java.util.ArrayList;
import java.util.Calendar;

import customExceptions.ActionsOnInActiveException;
import customExceptions.AlreadyUnActiveException;
import customExceptions.AreadyAddedIdException;
import customExceptions.NotEnoughtMoneyException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;
import dataStructure.*;

public class Bank {
	
	/*
	 * TODO
	 * creo	 que tiene un hash talbe de personas
	 * tiene filas que segun yo se manejan con colas
	 * tambien va aca lo del undo con el stack
	 */
	private ArrayList<Person> persons;
	private Heap<Person> priorityRow;
	private Queue<Person> normalRow;
	private HashTable<Integer, Person> dataBase;
	private Stack<Person> undo;
	
	public Bank() {
		persons = new ArrayList<>();
		priorityRow = new Heap<>(0, 0);
		normalRow = new Queue<>();
		dataBase = new HashTable<>();
		undo = new Stack<>();
	}
	
	public void addPerson(String name, int id, long ac, ArrayList<Card> cards, Calendar ing, 
			int age,boolean invalid, int gender, boolean pregnated) throws AreadyAddedIdException {
		for (int i = 0; i < persons.size(); i++) {
			if (id == persons.get(i).getId()) {
				throw new AreadyAddedIdException(id, persons.get(i).getName());
			}
		}
		persons.add(new Person(name, id, ac, cards, ing, age, invalid, gender, pregnated));
	}
	
	
	public void addPersonToRow(int id, String name) throws SmallerKeyException, UserIsNotRegiterException {
		int priority = 0;
		Person p = searchHash(id, name);//busqyeda efectiva... hashtable?
		if (p.getAge() >= 60) {
			priority++;
		}
		if (p.isInvalid()) {
			priority++;
		}
		if (p.getGender() == 0) {
			if (p.isPregnated()) {
				priority++;
			}
		}
		if (priority == 0) {
			normalRow.offer(p);
		} else {
			priorityRow.priorityInsert(priority, p);
		}		
	}

	public Person searchHash(int id, String name) throws UserIsNotRegiterException {
		Person p = dataBase.search(id);
		if (p == null) {
			throw new UserIsNotRegiterException(id, name);
		}
		return p;
	}
	
	public void consignment(Person p, int value) throws ActionsOnInActiveException {
		p.consignment(value);
	}
	
	public void withdrawals(Person p, int value) throws NotEnoughtMoneyException, ActionsOnInActiveException {
		p.withdrawals(value);
	}

	public void cancelAccount(Person p) throws AlreadyUnActiveException {
		p.cancelAccount();
	}
	
	public void activeAccount(Person p) {
		p.activeAccount();
	}
	
	//stack guardara objetos de tipo persona 5 operaciones
	
	//control Y
	
	//personas ya creadas y posibilidad de añadir
	
	//logica de pafo
	
}
