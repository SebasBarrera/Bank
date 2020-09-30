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
		Person p = searchHash(id, name);
		if (getPriority(p) == 0) {
			normalRow.offer(p);
		} else {
			if (priorityRow.getArraysize() == 0) {
				Person[] ps = new Person[1];
				ps[0] = p;
				priorityRow.setElements(ps);
				int[] prio = new int[1];
				prio[0] = getPriority(p);
			} else {
				priorityRow.priorityInsert(getPriority(p), p);
			}
		}		
	}
	
	public int getPriority(Person p) throws UserIsNotRegiterException {
		int priority = 0;
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
		
		return priority;
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
	
	public void addCard(Person p, int paymentDay, double fees, int quotas, double owe, double cardSpace) {
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
	
	/**
	 * @return the persons
	 */
	public ArrayList<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	/**
	 * @return the priorityRow
	 */
	public Heap<Person> getPriorityRow() {
		return priorityRow;
	}

	/**
	 * @param priorityRow the priorityRow to set
	 */
	public void setPriorityRow(Heap<Person> priorityRow) {
		this.priorityRow = priorityRow;
	}

	/**
	 * @return the normalRow
	 */
	public Queue<Person> getNormalRow() {
		return normalRow;
	}

	/**
	 * @param normalRow the normalRow to set
	 */
	public void setNormalRow(Queue<Person> normalRow) {
		this.normalRow = normalRow;
	}

	/**
	 * @return the dataBase
	 */
	public HashTable<Integer, Person> getDataBase() {
		return dataBase;
	}

	/**
	 * @param dataBase the dataBase to set
	 */
	public void setDataBase(HashTable<Integer, Person> dataBase) {
		this.dataBase = dataBase;
	}

	/**
	 * @return the undo
	 */
	public Stack<Person> getUndo() {
		return undo;
	}

	/**
	 * @param undo the undo to set
	 */
	public void setUndo(Stack<Person> undo) {
		this.undo = undo;
	}

	/**
	 * @return the redo
	 */
	public Stack<Person> getRedo() {
		return redo;
	}

	/**
	 * @param redo the redo to set
	 */
	public void setRedo(Stack<Person> redo) {
		this.redo = redo;
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
	
	public void order(int by) { // by es como se arreglara, 1 heapsort nombre, 2 mergesort cedula, 3 quicksort tiempor en orga, 4 bublesort monto					
		switch (by) {								
			case 1:
				Person[] p = new Person[persons.size()];
				int[] keys = new int[persons.size()];
				for (int i = 0; i < p.length; i++) {
					p[i] = persons.get(i);
					keys[i] = 0;
				}
				p = orderByNameHeapSort(p, keys);
				persons.clear();
				for (int i = 0; i < p.length; i++) {
					 persons.add(p[i]);
				}
			break;
			case 2:
				Person[] p1 = new Person[persons.size()];
				for (int i = 0; i < p1.length; i++) {
					 p1[i] = persons.get(i);
				}
				p1 = orderByIdMergeSort(p1);
				persons.clear();
				for (int i = 0; i < p1.length; i++) {
					 persons.add(p1[i]);
				}
			break;
			case 3:
				orderByTimeQuickSort();
			break;
			case 4:
				orderByAmountBubleSort();
			break;
		}
	}
	private void swap(int i, int j) {
		Person tmp = persons.get(i);
		persons.set(i, persons.get(j));
		persons.set(j, tmp);
	}
	private void orderByAmountBubleSort() {
		int n = persons.size();
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < (n-i); j++) {
				if(persons.get(j-1).compareByAmount(persons.get(j)) > 0) {
					swap(i, j);
				}
			}
		}
	}

	private void orderByTimeQuickSort() {
		
		int n = persons.size();
		qsort(0, n-1);
	}
	private void qsort(int lo, int hi) {
		if(lo < hi) {
			int p = partition(lo, hi);
			qsort(lo, p-1);
			qsort(p+1, hi);
		}
	}
	private int partition(int lo, int hi) {
		Person pivot = persons.get(hi);
		int i = lo;
		for(int j = lo; j < hi; j++) {
			if(persons.get(j).compareByIngress(pivot) < 0) {
				swap(i, j);
				i++;
			}
		}
		swap(i, hi);
		return i;
	}
	
	private Person[] orderByIdMergeSort(Person[] a) {
		
		int n = a.length;
		Person[] b = new Person[n];
		a = mSort(a, 0,n-1, b);
		return a;
	}
	
	private Person[] mSort(Person[] a, int lo, int hi, Person[] b) {
		if(lo < hi) {
			int mid = (lo+hi)/2;
			mSort(a, lo, mid, b);
			mSort(a, mid+1, hi, b);
			merge(a, lo, mid, hi, b);
		}
		return a;
	}
	
	private Person[] merge(Person[] a, int lo, int mid, int hi, Person[] b) {
		int i = lo;
		int j = mid+1;
		int k = lo;
		while(i<=mid && j <= hi) {
			if(a[i].compareById(a[j]) < 0) {
				b[k] = a[i];
				i++;
			}else {
				b[k] = a[j];
				j++;
			}
			k++;
		}
		while(i <= mid) {
			b[k] = a[i];
			i++;
			k++;
		}
		while(j <= hi) {
			b[k] = a[j];
			j++;
			k++;
		}
		for(k=lo; k <= hi; k++) {
			a[k] = b[k];
		}
		return a;
	}

	private Person[] orderByNameHeapSort(Person[] p, int[] keys) {
		
		Heap<Person> np = new Heap<Person>(0, 0);
		np.setKeys(keys);
		np.setElements(p);
		np.heapSort();	
		return np.getElements();
	}

	@Override
	public String toString() {
		return persons + "";
	}
	
	
	
}
