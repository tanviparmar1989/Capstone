package com.techelevator.view;

import com.techelevator.AuditLog;
import com.techelevator.VendingMachine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptions(Object[] options, VendingMachine machine) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options, machine);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptions(VendingMachine machine, Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(machine, options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
			AuditLog.log(e.getMessage());
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		//out.print(System.lineSeparator() + "Current Money Provided >>> ");
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private void displayMenuOptions(Object[] options, VendingMachine machine) {
		out.println("\n Best Ever Vending Machine \n");
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Current Money Provided >>> " + machine.getUserBalance()+"\n");
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private void displayMenuOptions(VendingMachine machine, Object[] options) {
		out.println("\n Best Ever Vending Machine \n");
		for (int i = 0; i < options.length-1; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		//out.print(System.lineSeparator() + "Current Money Provided >>> " + machine.getUserBalance()+"\n");
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}


}
