package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryConverterTest {

	private static long[] INTEGERS = new long[] { 0, 1, 100, 250 };
	private static String[] STRINGS = new String[] { "0", "1", "1100100", "11111010" };

	@Test
	void convertToTest() {
		BinaryConverter bc = new BinaryConverter();
		for (int i = 0; i < INTEGERS.length; i++) {
			assertTrue(bc.convertTo(INTEGERS[i]).equals(STRINGS[i]));
			assertFalse(bc.convertTo(INTEGERS[i]).equals("mockdata"));
		}
	}

	@Test
	void convertFromTest() {
		BinaryConverter bc = new BinaryConverter();
		for (int i = 0; i < INTEGERS.length; i++) {
			// check if the entries in arrays match
			assertEquals(bc.convertFrom(STRINGS[i]), INTEGERS[i]);
			// but non of the strings should return -1
			assertFalse(bc.convertFrom(STRINGS[i]) == -1);
		}
	}

	// Also check if converter can drop extra zeros
	@Test
	void dropZerosTest() {
		BinaryConverter bc = new BinaryConverter();
		assertEquals(bc.convertFrom("0001"), 1);
	}

	// Also test with incorrect input:

	@Test
	void incorrectInputTest() {
		BinaryConverter bc = new BinaryConverter();
		assertNull(bc.convertTo(-1));
		String[] testValues = new String[] { "110120", "foo", "", "00011012" };
		for (int i = 0; i < testValues.length; i++) {
			assertEquals(bc.convertFrom(testValues[i]), -1);
		}
	}

}
