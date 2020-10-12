package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import customExceptions.AreadyAddedIdException;

class BankTest {

	private Bank bank;
	
	private void setupStage() throws AreadyAddedIdException {
		bank = new Bank();
		
		Calendar c1 = Calendar.getInstance();
		bank.addPerson("Sebastián Barrera", 11441050, null, c1, 71, false, Person.MALE, false);
		bank.addCard(bank.getPersons().get(0), 25, 1.8, 6, 2000.0, 30000.0);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(2021, Calendar.JUNE, 22);
		bank.addPerson("Jhon Arboleda    ", 64236302, null, c2, 65, true, Person.MALE, false);
		bank.addCard(bank.getPersons().get(1), 18, 2.0, 6, 5000.0, 20000.0);
		bank.addCard(bank.getPersons().get(1), 15, 1.2, 12, 50000.0, 200000.0);
		
		Calendar c3 = Calendar.getInstance();
		c3.set(2020, Calendar.OCTOBER, 12);
		bank.addPerson("Alejandro García ", 87634353, null, c3, 20, false, Person.MALE, false);
		bank.addCard(bank.getPersons().get(2), 5, 2.4, 18, 20000.0, 200000.0);
		bank.addCard(bank.getPersons().get(2), 5, 2.1, 24, 80000.0, 100000.0);
		bank.addCard(bank.getPersons().get(2), 5, 2.1, 24, 29000.0, 300000.0);
	}
	
	@Test
	void orderByNameHeapSort() throws AreadyAddedIdException {
		setupStage();
		bank.order(1);
		/*System.out.println();
		System.out.println("SORTED BY NAME BY HEAP" + bank.toString());
		System.out.println();*/
		for (int i = 0; i < bank.getPersons().size() - 1; i++) {
			int j = i + 1;
			assertTrue(bank.getPersons().get(j).getName().compareTo(bank.getPersons().get(i).getName()) >= 0);
		}
	}
	
	@Test
	void orderByIdMergeSort() throws AreadyAddedIdException {
		setupStage();
		bank.order(2);
		/*System.out.println();
		System.out.println("SORTED BY ID BY MERGE" + bank.toString());
		System.out.println();*/
		for (int i = 0; i < bank.getPersons().size() - 1; i++) {
			int j = i + 1;
			assertTrue(bank.getPersons().get(j).getId() >= bank.getPersons().get(i).getId());
		}
	}
	
	@Test
	void orderByTimeQuickSort() throws AreadyAddedIdException {
		setupStage();
		bank.order(3);
		/*System.out.println();
		System.out.println("SORTED BY TIME BY QUICK" + bank.toString());
		System.out.println();*/
		for (int i = 0; i < bank.getPersons().size() - 1; i++) {
			int j = i + 1;
			assertTrue(bank.getPersons().get(j).getIngress().compareTo(bank.getPersons().get(i).getIngress()) >= 0);
		}
	}
	
	@Test
	void orderByAmountBubuleSort() throws AreadyAddedIdException {
		setupStage();
		bank.order(4);
		/*System.out.println();
		System.out.println("SORTED BY AMOUNT BY BUBLE" + bank.toString());
		System.out.println();*/
		for (int i = 0; i < bank.getPersons().size() - 1; i++) {
			int j = i + 1;
			assertTrue(bank.getPersons().get(j).compareByAmount(bank.getPersons().get(i)) >= 0);
		}
	}

	
}
