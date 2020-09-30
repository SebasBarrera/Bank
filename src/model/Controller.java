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
}
