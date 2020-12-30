package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HexConverterTest {

	private static long[] INTEGERS = new long[] { 0, 1, 20, 100, 1506 };
	private static String[] STRINGS = new String[] { "0", "1", "14", "64", "5e2" };

	@Test
	void convertToTest() {
		HexConverter hc = new HexConverter();
		for (int i = 0; i < INTEGERS.length; i++) {
			assertTrue(hc.convertTo(INTEGERS[i]).equals(STRINGS[i]));
			assertFalse(hc.convertTo(INTEGERS[i]).equals("mockdata"));
		}
	}

	@Test
	void convertFromTest() {
		HexConverter hc = new HexConverter();
		for (int i = 0; i < INTEGERS.length; i++) {
			// check if the entries in arrays match
			assertEquals(hc.convertFrom(STRINGS[i]), INTEGERS[i]);
			// but non of the strings should return -1
			assertFalse(hc.convertFrom(STRINGS[i]) == -1);
		}
	}

	@Test
	void dropZerosTest() {
		HexConverter hc = new HexConverter();
		assertEquals(hc.convertFrom("000f"), 15);
	}

	// make sure we can handle incorrect input
	@Test
	void incorrectInputTest() {
		HexConverter hc = new HexConverter();
		assertNull(hc.convertTo(-1));
		String[] testValues = new String[] { "a9gcf", "foo", "", "-94d8" };
		for (int i = 0; i < testValues.length; i++) {
			assertEquals(hc.convertFrom(testValues[i]), -1);
		}
	}

}
