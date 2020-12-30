package numberconverter;

import gui.GUI;
import cli.CLI;
import logic.Converter;
import logic.BinaryConverter;
import logic.HexConverter;
import javafx.application.Application;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		List<Converter> converters = new ArrayList<>();
		// add all converters here
		converters.add(new BinaryConverter());
		converters.add(new HexConverter());

		while (true) {
			System.out.print("Choose interface (cli/gui): ");
			String input = reader.nextLine();
			if (input.equalsIgnoreCase("gui")) {
				Application.launch(GUI.class, "--title=Number Converter");
				break;
			} else if (input.equalsIgnoreCase("cli")) {
				CLI cli = new CLI(reader, converters);
				cli.start();
				break;
			}
		}
	}
}
