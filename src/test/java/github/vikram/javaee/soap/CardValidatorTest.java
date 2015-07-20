package github.vikram.javaee.soap;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardValidatorTest {
	 
	  @Test
	  public void shouldCheckCreditCardValidity() {
	 
	    CardValidator cardValidator = new CardValidator();
	 
	    CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
	    assertTrue("Credit card should be valid", cardValidator.validate(creditCard) );
	 
	    creditCard.setNumber("12341233");
	    assertFalse("Credit card should not be valid", cardValidator.validate(creditCard) );
	  }
}
