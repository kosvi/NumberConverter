package numberconverter;

import gui.GUI;
import cli.CLI;
import logic.Converter;
import logic.BinaryConverter;
import javafx.application.Application;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		List<Converter> converters = new ArrayList<>();
		converters.add(new BinaryConverter());
		CLI cli = new CLI(reader, converters);
		cli.start();
	}
}
