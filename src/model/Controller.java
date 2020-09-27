package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import customExceptions.AreadyAddedIdException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;

public class Controller {
		
	private Bank control;
	private Scanner sc;
	
	public Controller() {
		sc = new Scanner(System.in);
		
		control = new Bank();
		Calendar ing = Calendar.getInstance();
		Calendar paymentDate = Calendar.getInstance();
		Calendar dueDate = Calendar.getInstance();
		ing.set(2016, 6, 15);
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(765432, paymentDate, 234, dueDate, 92, 48, 1000000.0, 2000000.0);
		cards.add(card1);
		try {
			control.addPerson("Jon Z", 14793, 28596313, cards, ing, 19, true, 1, false);
		} catch (AreadyAddedIdException e) {
			e.getMessage();
		}
	}
	
	public void queueQueue() {
		String name = sc.nextLine();//INTERFAZ LOS PIDE
		int id = sc.nextInt();sc.nextLine();//INTERFAZ LOS PIDE
		try {
			control.addPersonToRow(id, name);
		} catch (SmallerKeyException | UserIsNotRegiterException e) {
			e.getMessage();
		}
	}
	
	
}
