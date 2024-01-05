package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("DKK", DanskeBank.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		//Błąd w metodzie openAccount()
				SweBank.openAccount("Julia");
				SweBank.deposit("Julia", new Money(1000000, SEK));
				assertEquals(1000000, SweBank.getBalance("Julia").intValue());
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		//Błąd w metodzie Deposit() 
		SweBank.deposit("Ulrika", new Money(500, SEK));
		assertEquals(500, SweBank.getBalance("Ulrika").intValue());
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		//Błąd w metodzie Withdraw()
		SweBank.withdraw("Ulrika", new Money(200, SEK));
		assertEquals(-200, SweBank.getBalance("Ulrika").intValue());
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		assertEquals(10000, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		   //Błąd w metodzie Transfer ()
		        //w innych bankach
				SweBank.deposit("Bob", new Money(1000, SEK));
				SweBank.transfer("Bob", Nordea, "Bob", new Money(1000, SEK));
				assertEquals(0, SweBank.getBalance("Bob").intValue());
				assertEquals(1000, Nordea.getBalance("Bob").intValue());
				// w tym samym banku
				SweBank.deposit("Bob", new Money(1000, SEK));
				SweBank.transfer("Bob", "Ulrika", new Money(1000, SEK));
				assertEquals(0, SweBank.getBalance("Bob").intValue()); 
				assertEquals(1000, SweBank.getBalance("Ulrika").intValue());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000, SEK));
		SweBank.addTimedPayment("Bob", "1", 10, 0, new Money(100000, SEK), SweBank, "Ulrika");
		SweBank.tick();
		assertEquals(0, SweBank.getBalance("Bob").intValue());
	}
}
