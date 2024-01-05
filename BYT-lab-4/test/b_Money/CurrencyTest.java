package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		// Sprawdzam czy metoda getName zwraca poprawną nazwę waluty
		assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		// Sprawdzam czy metoda getRate zwraca poprawny kurs waluty
		assertEquals(0.15, SEK.getRate(), 0.001);
	}
	
	@Test
	public void testSetRate() {
		// Sprawdzam czy metoda setRate ustawia poprawny kurs waluty
		SEK.setRate(0.25);
		assertEquals(0.25, SEK.getRate(), 0.001);
	}
	
	@Test
	public void testGlobalValue() {
		// Sprawdzam czy metoda universalValue przelicza poprawnie kwotę waluty na uniwersalną
		var pom = EUR.universalValue(100);
		assertEquals(Optional.of(150).get(), pom);
	}
	
	@Test
	public void testValueInThisCurrency() {
		// Sprawdzam czy metoda valueInThisCurrency przelicza poprawnie kwotę z jednej waluty na drugą
		var pom = EUR.valueInThisCurrency(1000, SEK);
		assertEquals(Optional.of(100).get(), pom);
	}

}