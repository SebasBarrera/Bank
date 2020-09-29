package model;

import java.util.ArrayList;
import java.util.Calendar;
import customExceptions.ActionsOnInactiveException;
import customExceptions.AlreadyInactiveException;
import customExceptions.AlreadyPaidException;
import customExceptions.AreadyAddedIdException;
import customExceptions.HeapUnderFlowException;
import customExceptions.NormalRowIsEmptyException;
import customExceptions.NotEnoughtMoneyException;
import customExceptions.NotFoundCardException;
import customExceptions.NothingToRedoException;
import customExceptions.NothingToUndoException;
import customExceptions.PriorityRowIsEmptyException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;
import dataStructure.*;


public class Bank {
	
	private Person[] p;
	private ArrayList<Person> persons;
	private Heap<Person> priorityRow;
	private Queue<Person> normalRow;
	private HashTable<Integer, Person> dataBase;
	private HashTable<Integer, Person> dataBaseOut;
	private Stack<Person> undo;
	private Stack<Person> redo;
	
	public Bank() {
		priorityRow = new Heap<>(0, 0);
		normalRow = new Queue<>();
		dataBase = new HashTable<>();
		dataBaseOut = new HashTable<>();
		undo = new Stack<>();
		redo = new Stack<>();
		persons = new ArrayList<>();
	}
	
	public void addPerson(String name, int id, ArrayList<Card> cards, Calendar ing, 
			int age,boolean invalid, int gender, boolean pregnated) throws AreadyAddedIdException {
		Person there = dataBase.search(id);
		if (there != null) {
			throw new AreadyAddedIdException(id, there.getName());
		}
		long ac = newAccountNumber();
		Person p = new Person(name, id, ac, cards, ing, age, invalid, gender, pregnated);
		dataBase.insert(id, p);
		persons.add(p);
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
	
	public void consignment(Person p, int value) throws ActionsOnInactiveException, AlreadyInactiveException {
		undo.push(p);
		p.consignment(value);
	}
	
	public void withdrawals(Person p, int value) throws NotEnoughtMoneyException, ActionsOnInactiveException, AlreadyInactiveException {
		undo.push(p);
		p.withdrawals(value);
	}

	public void cancelAccount(Person p) throws AlreadyInactiveException {
		undo.push(p);
		dataBaseOut.insert(p.getId(), p);
		p.cancelAccount();
		dataBase.delete(p.getId());
		persons.remove(p);
	}
	
	/*public void activeAccount(Person p) throws AlreadyActiveException {
		undo.push(p);
		
		p.activeAccount();
	}
	*/
	
	/**
	 * description you can only pay the full card or the fee
	 * @param total If total is true pay the entire card, if not pay a fee
	 * @param cuentaAhorro If cuentaAhorros is true pay with savings account, if not pay cash
	*/
	public void payCard(Person p, long number, boolean total, boolean cuentaAhorros) throws NotFoundCardException, AlreadyPaidException, AlreadyInactiveException, NotEnoughtMoneyException, ActionsOnInactiveException {// si total es true paga toda la tarjeta, si no paga una cuota
		undo.push(p);
		p.payCard(number, total, cuentaAhorros);
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
		for (int i = 0; i < HashTable.ARRAY_SIZE && !equals; i++) {
			Person p = dataBase.search(i);
			if (p != null) {
				equals = p.searchCardToCreate((long)number);
			}
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
		for (int i = 0; i < HashTable.ARRAY_SIZE && !equals; i++) {
			Person p = dataBase.search(i);
			if (p != null) {
				if (p.getAccountNumber() != (long) number) {
					equals = true;
				}
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

	/**
	 * @return the dataBaseOut
	 */
	public HashTable<Integer, Person> getDataBaseOut() {
		return dataBaseOut;
	}

	/**
	 * @param dataBaseOut the dataBaseOut to set
	 */
	public void setDataBaseOut(HashTable<Integer, Person> dataBaseOut) {
		this.dataBaseOut = dataBaseOut;
	}
	
	public Person AttendNormalRow() throws NormalRowIsEmptyException {
		Person p = null;
		if (!normalRow.isEmpty()) {
			p = normalRow.peek();
		} else {
			throw new NormalRowIsEmptyException();
		}
		return p;
	}

	public Person AttendPriorityRow() throws HeapUnderFlowException, PriorityRowIsEmptyException {
		Person p = null;
		if (priorityRow.getArraysize() != 0) {
			p = priorityRow.extractMaxheap();
		} else {
			throw new PriorityRowIsEmptyException();
		}
		return p;
	}
	
	public Person[] order(int by) { // by es como se arreglara, 1 heapsort nombre, 2 mergesort cedula, 3 quicksort tiempor en orga, 4 bublesort monto
		p = castToArray();					
		switch (by) {								
			case 1:
				p = orderByNameHeapSort(p);
			break;
			case 2:
				p = orderByIdMergeSort(p);
			break;
			case 3:
				p = orderByTimeQuickSort(p);
			break;
			case 4:
				p = orderByAmountBubleSort(p);
			break;
		}
		return p;
	}
	
	private Person[] orderByAmountBubleSort(Person[] p) {
		// TODO Auto-generated method stub
		return p;
	}

	private Person[] orderByTimeQuickSort(Person[] p) {
		// TODO Auto-generated method stub
		return p;
	}

	private Person[] orderByIdMergeSort(Person[] p) {
		// TODO Auto-generated method stub
		return p;
	}

	private Person[] orderByNameHeapSort(Person[] p) {
		Heap<Person> np = new Heap<Person>(0, 0);
		np.setElements(p);
		np.heapSort();	
		return np.getElements();
	}

	public Person[] castToArray() {
		Person[] p = new Person[persons.size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = persons.get(i);
		}
		return p;
	}

	/**
	 * @return the p
	 */
	public Person[] getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Person[] p) {
		this.p = p;
	}
	
}
