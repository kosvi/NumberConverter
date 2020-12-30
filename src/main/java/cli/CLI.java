package cli;

import java.util.Scanner;
import java.util.List;

import logic.Converter;

public class CLI {

	private Scanner reader;
	private List<Converter> converters;
	private int selectedConverter;

	// these are user inputs
	private long digit;

	public CLI(Scanner reader, List<Converter> converters) {
		this.reader = reader;
		this.converters = converters;
		this.selectedConverter = 0;
		this.digit = 0;
	}

	public void start() {
		while (true) {
			// we will take user input here
			String input = "";
			// let's start by printing menu-options
			this.printMenu();
			System.out.print("> ");
			try {
				input = this.reader.nextLine();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			if (input.equalsIgnoreCase("q")) {
				break;
			}
			// if user didn't want to exit, let's use another method to handle input
			this.handleMenuInput(input);
		}
		System.out.println("Goodbye!");
	}

	private void printMenu() {
		System.out.println("");
		System.out.println("Current number: " + this.digit + " = "
				+ this.converters.get(this.selectedConverter).convertTo(this.digit) + " ("
				+ this.converters.get(this.selectedConverter).getName() + ")");
		System.out.println("1) change converter");
		System.out.println("2) convert from 10-base");
		System.out.println("3) convert to 10-base");
		System.out.println("Q) quit");
	}

	private void handleMenuInput(String input) {
		int value = -1;
		try {
			value = Integer.valueOf(input);
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		switch (value) {
		case 1:
			this.selectConverter();
			break;
		case 2:
			this.convertTo();
			break;
		case 3:
			this.convertFrom();
			break;
		default:
			break;
		}
	}

	private void selectConverter() {
		System.out.println("Select converter you want to use: ");
		// n will be our index for the menu
		int n = 0;
		for (Converter c : this.converters) {
			// our menu start from 1
			n++;
			System.out.println(n + ") " + c.getName());
		}
		int value = -1;
		System.out.print("> ");
		try {
			value = Integer.valueOf(this.reader.nextLine());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		// since all converters were printed with index + 1, we now need to reduce 1
		if (value > 0 && value <= this.converters.size()) {
			this.selectedConverter = value - 1;
		}
	}

	private void convertTo() {
		System.out.println("Give a positive integer: ");
		System.out.print("> ");
		long value = -1;
		try {
			value = Integer.valueOf(this.reader.nextLine());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		String digitInOtherFormat = null;
		if (value >= 0) {
			this.digit = value;
			digitInOtherFormat = this.converters.get(this.selectedConverter).convertTo(value);
		}
		if (digitInOtherFormat != null) {
			System.out.println("\n" + this.digit + " is " + digitInOtherFormat + " in "
					+ this.converters.get(this.selectedConverter).getName() + "-format\n");
		} else {
			System.out.println("\nInvalid input or too big integer.\n");
		}
		System.out.print("press <Enter> to continue...");
		this.reader.nextLine();
	}

	private void convertFrom() {
		System.out.println("Give input as " + this.converters.get(this.selectedConverter).getName() + ":");
		System.out.print("> ");
		String input = null;
		try {
			input = this.reader.nextLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		long value = this.converters.get(this.selectedConverter).convertFrom(input);
		if (value > 0) {
			this.digit = value;
			System.out.println("\n" + input + " is " + value + " if converted from "
					+ this.converters.get(this.selectedConverter).getName() + "-format\n");
		} else {
			System.out.println("\nInvalid input or too big number.\n");
		}
		System.out.print("press <Enter> to continue...");
		this.reader.nextLine();
	}
}
