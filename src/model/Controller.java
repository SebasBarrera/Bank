package model;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import customExceptions.ActionsOnInactiveException;
import customExceptions.AlreadyActiveException;
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

public class Controller {
		
	private Bank control;
	private Scanner sc;
	
	public Controller() throws AreadyAddedIdException {
		sc = new Scanner(System.in);
		
		control = new Bank();
		Calendar c1 = Calendar.getInstance();
		control.addPerson("Juan Sebastian Barrera Pulido", 1144105003, null, c1, 21, false, Person.MALE, false);
		control.addCard(control.getPersons().get(0), 25, 1.8, 6, 2000000.0, 3000000.0);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(2020, Calendar.AUGUST, 22);
		control.addPerson("Jhon Arboleda", 64236302, null, c2, 18, false, Person.MALE, false);
		control.addCard(control.getPersons().get(1), 18, 2.0, 6, 500000.0, 2000000.0);
		control.addCard(control.getPersons().get(1), 15, 1.2, 12, 5000000.0, 200000000.0);
		
		Calendar c3 = Calendar.getInstance();
		c3.set(2021, Calendar.OCTOBER, 22);
		control.addPerson("Alejandro Garcia", 8763435, null, c3, 20, false, Person.MALE, false);
		control.addCard(control.getPersons().get(2), 5, 2.4, 18, 2000000.0, 2000000.0);
		control.addCard(control.getPersons().get(2), 5, 2.1, 24, 8000000.0, 100000000.0);
		control.addCard(control.getPersons().get(2), 5, 2.1, 24, 2900000.0, 300000000.0);
	}
	
	public void addToRow(int id, String name) throws AreadyAddedIdException, SmallerKeyException, UserIsNotRegiterException {
		control.addPersonToRow(id, name);
	}
	
	public void deposit(Person p, int value) throws ActionsOnInactiveException, AlreadyInactiveException {
		control.consignment(p, value);
	}
	
	public void withdraw(Person p, int value) throws NotEnoughtMoneyException, ActionsOnInactiveException, AlreadyInactiveException {
		control.withdrawals(p, value);
	}
	
	public void cancelAcc(Person p, String why) throws AlreadyInactiveException {
		control.cancelAccount(p);
	}
	
	public void cardPayment(Person p, long cardNumber, boolean all, boolean accType) throws NotFoundCardException, AlreadyPaidException, AlreadyInactiveException, NotEnoughtMoneyException, ActionsOnInactiveException {
		control.payCard(p, cardNumber, all, accType);
	}
	
	public void addCard(Person p, double debt, double fit, int quotas, int fees, int paymentDay) {
		control.addCard(p, paymentDay, fees, quotas, debt, fit);
	}
	
	public Person getNextInPriotityRow() throws HeapUnderFlowException, PriorityRowIsEmptyException {
		return control.getNextInPriorityRow();
	}
	
	public Person getNextInNormalRow() throws NormalRowIsEmptyException {
		return control.getNextInNormalRow();
	}
	
	public void undo(Person p) throws NothingToUndoException {
		control.undo(p);
	}
	
	public void redo(Person p) throws NothingToRedoException {
		control.redo(p);
	}
	
	public void addPerson(String name, int id, int age, boolean disabled, int gender, boolean pregnated) throws AreadyAddedIdException {
		//siempre empieza sin tarjetas
		Calendar ing = Calendar.getInstance();
		control.addPerson(name, id, null, ing, age, disabled, gender, pregnated);
	}
	
	public Person findUser(int id, String name) throws UserIsNotRegiterException {
		return control.searchHash(id, name);
	}
	
	public int getPriority(Person p) throws UserIsNotRegiterException {
		return control.getPriority(p);
	}
	
	public List<Person> getPersons() {
		return control.getPersonList();
	}
	
	public void extractInPriorityQ() throws HeapUnderFlowException {
		control.extractInPriorityQ();
	}
	
	public void peekInNormalQ() {
		control.peekInNormalQ();
	}
}
