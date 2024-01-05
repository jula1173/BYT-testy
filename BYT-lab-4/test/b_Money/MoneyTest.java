package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		// Sprawdzam czy metoda getAmount zwraca poprawną wartość
				assertEquals(10000.0, SEK100.getAmount(), 0.001);
	}

	@Test
	public void testGetCurrency() {
		// Sprawdzam czy metoda getCurrency zwraca poprawną walutę
		assertEquals(SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() {
		// Sprawdzam czy metoda toString generuje poprawny ciąg znaków
			assertEquals("100.0 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(SEK.universalValue(10000), SEK100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		// Sprawdzam czy dwie instancje Money o takich samych wartościach są równe
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEK100.equals(EUR0));
	}

	@Test
	public void testAdd() {
		// Sprawdzam czy dodawanie dwóch wartosci zwraca poprawny wynik
		var result = SEK100.add(EUR10);
		assertEquals(Optional.of(20000.0).get(), result.getAmount());
	}

	@Test
	public void testSub() {
		// Sprawdzam czy odejmowanie dwóch wartosci zwraca poprawny wynik
		var result = SEK100.sub(EUR10);
		assertEquals(Optional.of(0.0).get(), result.getAmount());
	}

	@Test
	public void testIsZero() {
		// Sprawdzam czy metoda isZero poprawnie określa, czy Money ma wartość zerową
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
	}

	@Test
	public void testNegate() {
		// Sprawdzam czy negacja Money zwraca poprawny wynik
		assertEquals(Optional.of(-10000.0).get(), SEK100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		// Sprawdzam czy metoda compareTo porównuje dwie instancje Money poprawnie
		assertEquals(-1, SEK0.compareTo(EUR10));
		assertEquals(1, SEK200.compareTo(EUR10));
	}
}