package model;

import java.util.ArrayList;
import java.util.Calendar;
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
		Calendar ing = Calendar.getInstance();
		int paymentDate = 10;
		ing.set(2016, 6, 15);
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(765432, paymentDate, 234, 92, 48, 1000000.0, 2000000.0);
		cards.add(card1);
		control.addPerson("Jon Z", 14793, cards, ing, 19, true, 1, false);
	}
	
	public void addToRow() throws AreadyAddedIdException {
		String name = sc.nextLine();//INTERFAZ LOS PIDE
		int id = sc.nextInt();sc.nextLine();//INTERFAZ LOS PIDE
		try {
			control.addPersonToRow(id, name);
		} catch (SmallerKeyException e) {
			e.getMessage();
		} catch (UserIsNotRegiterException e) {
			e.getMessage();
			addPerson(name, id);
		}
		
	}
	
	public void AttendARow() throws UserIsNotRegiterException, ActionsOnInactiveException, NotEnoughtMoneyException, AlreadyInactiveException, AlreadyActiveException, NotFoundCardException, AlreadyPaidException, NothingToUndoException, NothingToRedoException, NormalRowIsEmptyException, HeapUnderFlowException, PriorityRowIsEmptyException {
		boolean cual; // decide en cual de las dos colas se atendera la siguiente persona
		int priority = sc.nextInt();
		if (priority == 1) {
			cual = true;
		} else {
			cual = false;
		}
		Person p;
		if (cual) {
			p = control.AttendPriorityRow();
		} else {
			p = control.AttendNormalRow();
		}
		String what = sc.nextLine(); // que accion querra hacer
		boolean getOut = false;
		int value;
		while (!getOut) {
			switch (what) {
				case "consignment":
					value = sc.nextInt();sc.nextLine(); // cuanto consignara
					control.consignment(p, value);
				break;
				case "withdrawals":
					value = sc.nextInt();sc.nextLine(); // cuanto retirara
					control.withdrawals(p, value);
				break;
				case "cancelAccount":
					control.cancelAccount(p);
					@SuppressWarnings("unused") String motivo = ""; // razon por la cual cancela la cuenta
					@SuppressWarnings("unused") Calendar hoy = Calendar.getInstance(); //fecha en la que se retira
				break;
				case "payCard":
					long number = sc.nextLong();sc.nextLine(); // numero de la tarjeta que pagara
					int comoPagara = sc.nextInt(); sc.nextLine(); // aqui se decide si el usuario pagara toda la deuda de la tarjeta, 
																	//o solo la siguiente cuota
					int cuentaDeAhorros = sc.nextInt(); sc.nextLine();  //indica si pagara en efectivo o con la cuenta de ahorros;
					boolean total; // true si paga toda la tarjeta, false si paga una cuota
					boolean cuentaAhorros; // true si paga con cuenta de ahorros, false si en efectivo
					if (comoPagara == 1) {
						total = true;
					} else {
						total = false;
					}
					if (cuentaDeAhorros == 1) {
						cuentaAhorros = true;
					} else {
						cuentaAhorros = false;
					}
					control.payCard(p, number, total, cuentaAhorros);;
				break;
				case "addCard":
					double owe = sc.nextDouble();sc.nextLine(); // de cuanto sera la deuda de la tarjeta
					double cardSpace = sc.nextDouble(); sc.nextLine(); // cupo de la tarjeta
					int quotas = sc.nextInt();sc.nextLine(); // nº de cuotas
					// LA TASA ES UN NUMERO ENTRE 1.4452 Y 2.0798,
					int fees = sc.nextInt();sc.nextLine(); // interes periodico  mensual vencido
					// SIENDO LA TASA MAS COMUN 1.8715, PODRIAMOS MANEJARLO CON OPCIONES QUE TOMA LA CAJERA
					int paymentDay =sc.nextInt(); sc.nextLine(); //dia de pago del mes
					control.addCard(p, paymentDay, fees, quotas, owe, cardSpace);		
				break;
				case "undo":
					control.undo(p);
				break;
				case "redo":
					control.redo(p);
				break;
				case "getOut":
					getOut = true;
				break;
			}
		}
	}
	
	public void addPerson(String name, int id) throws AreadyAddedIdException {
		//siempre empieza sin tarjetas
		Calendar ing = Calendar.getInstance();
		int age = sc.nextInt(); sc.nextLine(); //cuantos años
		int invalido = sc.nextInt(); sc.nextLine(); // 0 si es invalido, 1 si no
		boolean invalid;
		if (invalido == 0) {
			invalid = true;
		} else {
			invalid = false;
		}
		int gender = sc.nextInt(); sc.nextLine(); // 0 si es invalido, 1 si es hombre
		boolean pregnated = false;
		if (gender == Person.FEMALE) {
			int preg = sc.nextInt(); sc.nextLine(); // 0 si esta embarazada, 1 si no
			if (preg == 0) {
				pregnated = true;
			}
		}	
		control.addPerson(name, id, null, ing, age, invalid, gender, pregnated);
	}
	
}
