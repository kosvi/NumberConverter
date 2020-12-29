package logic;

import java.lang.StringBuilder;

public class BinaryConverter implements Converter {

	private static int BINARY_MAX_LENGTH = 20;
	private static String CONVERTER_NAME = "Binary";

	@Override
	public String getName() {
		return CONVERTER_NAME;
	}

	@Override
	public String convertTo(long input) {
		// We don't care for negatives or too big numbers
		if (input < 0 || !this.checkIntegerInput(input)) {
			return null;
		}
		// 0 = 0 even as binary
		if (input == 0) {
			return "0";
		}
		StringBuilder binaryString = new StringBuilder();
		// First append every remainder in the string
		while (input > 0) {
			binaryString.append(input % 2);
			input /= 2;
		}
		// and then reverse it
		binaryString.reverse();
		return binaryString.toString();
	}

	@Override
	public long convertFrom(String input) {
		if (!this.checkStringInput(input)) {
			return -1;
		}
		long sum = 0;
		// I need the last index to start loop from there
		// Another solution would have been to reverse the string (as in convertTo() )
		int lastIndex = input.length() - 1;
		for (int i = lastIndex; i >= 0; i--) {
			if (input.charAt(i) == '1') {
				// if current bit is 1 -> add it's value to the sum
				sum += (int) Math.pow(2, lastIndex - i);
			}
		}
		return sum;
	}

	private boolean checkStringInput(String input) {
		// Let's limit the length of our binary numbers
		if (input.length() > BINARY_MAX_LENGTH) {
			return false;
		}
		// Binary number can only include 0's and 1's
		if (!input.matches("[0|1]+")) {
			return false;
		}
		return true;
	}

	private boolean checkIntegerInput(long input) {
		if(input>=(((int) Math.pow(2, BINARY_MAX_LENGTH)*2))) {
			return false;
		}
		return true;
	}

}
