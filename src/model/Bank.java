package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import customExceptions.AreadyAddedIdException;
import customExceptions.SmallerKeyException;
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
	
	public Bank() {
		persons = new ArrayList<>();
		priorityRow = new Heap<>(0, 0);
		normalRow = new Queue<>();
		dataBase = new HashTable<>();
	}
	
	public void addPerson(String name, int id, long ac, ArrayList<Card> cards, Calendar ing, int age, boolean invalid, int gender, boolean pregnated) throws AreadyAddedIdException {
		for (int i = 0; i < persons.size(); i++) {
			if (id == persons.get(i).getId()) {
				throw new AreadyAddedIdException(id, persons.get(i).getName());
			}
		}
		persons.add(new Person(name, id, ac, cards, ing, age, invalid, gender, pregnated));
	}
	
	public void addPersonToRow(int id) throws SmallerKeyException {
		int priority = 0;
		Person p = searchHash(id);//busqyeda efectiva... hashtable?
		if (p != null) {
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
			
	}

	public Person searchHash(int id) {
		Person p = dataBase.search(id);
		return p;
	}
	
	

	//hashtable para la busquedas
	
	//stack guardara objetos de tipo persona 5 operaciones
	
	//control Y
	
	//personas ya creadas y posibilidad de añadir
	
	//logica de pafo
	
}
