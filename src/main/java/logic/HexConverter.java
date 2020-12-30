package logic;

public class HexConverter implements Converter {

	private static String CONVERTER_NAME = "Hex";
	private static int HEX_MAX_LENGTH = 8;

	@Override
	public String convertTo(long input) {
		// We don't care for negatives or too big numbers
		if (input < 0 || !this.checkIntegerInput(input)) {
			return null;
		}
		// 0 = 0 even as hex
		if (input == 0) {
			return "0";
		}
		StringBuilder hexString = new StringBuilder();
		// First append every remainder in the string
		while (input > 0) {
			int remainder = (int) input % 16;
			hexString.append(this.convertIntToChar(remainder));
			input /= 16;
		}
		// and then reverse it
		hexString.reverse();
		return hexString.toString().toLowerCase();
	}

	@Override
	public long convertFrom(String input) {
		input = input.toLowerCase();
		if (!this.checkStringInput(input)) {
			return -1;
		}
		long sum = 0;
		// I need the last index to start loop from there
		// Another solution would have been to reverse the string
		int lastIndex = input.length() - 1;
		for (int i = lastIndex; i >= 0; i--) {
			if (input.charAt(i) != '0') {
				sum += (int) Math.pow(16, lastIndex - i) * this.convertCharToInt(input.charAt(i));
			}
		}
		return sum;
	}

	@Override
	public String getName() {
		return CONVERTER_NAME;
	}

	// has to be smarter way, but I don't want to use existing conversion-methods
	// as this is my practice
	private int convertCharToInt(char c) {
		switch (c) {
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		default:
			return 0;
		}
	}

	// I wouldn't wanna have used this, but Im lazy...
	private String convertIntToChar(int i) {
		return Integer.toHexString(i);
	}

	private boolean checkStringInput(String input) {
		// Let's limit the length of our hex numbers
		if (input.length() > HEX_MAX_LENGTH) {
			return false;
		}
		// Hex number can only include 0-9 and a-f
		input = input.toLowerCase();
		if (!input.matches("[a-f0-9]+")) {
			return false;
		}
		return true;
	}

	private boolean checkIntegerInput(long input) {
		if (input >= (((long) Math.pow(16, HEX_MAX_LENGTH)) * 2)) {
			return false;
		}
		return true;
	}

}
