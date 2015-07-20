package github.vikram.javaee.soap;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.junit.Test;

public class CardValidatorIT {
	 
	  @Test
	  public void shouldCheckCreditCardValidity() throws MalformedURLException {
	 
	    // Publishes the SOAP Web Service
	    Endpoint endpoint = Endpoint.publish("http://localhost:8282/cardValidator", new CardValidator());
	    assertTrue( endpoint.isPublished() );
	    assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());
	 
	    // Needed properties to access the web service
	    URL wsdlDocumentLocation = new URL("http://localhost:8282/cardValidator?wsdl");
	    String namespaceURI = "http://soap.javaee.vikram.github/";
	    String servicePart = "CardValidatorService";
	    String portName = "CardValidatorPort";
	    QName serviceQN = new QName(namespaceURI, servicePart);
	    QName portQN = new QName(namespaceURI, portName);
	 
	    // Creates a service instance
	    Service service = Service.create(wsdlDocumentLocation, serviceQN);
	    Validator cardValidator = service.getPort(portQN, Validator.class);
	 
	    // Invokes the web service
	    CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
	    assertTrue("Credit card should be valid", cardValidator.validate(creditCard) );
	 
	    creditCard.setNumber("12341233");
	    assertFalse("Credit card should not be valid", cardValidator.validate(creditCard) );
	 
	    // Unpublishes the SOAP Web Service
	    endpoint.stop();
	    assertFalse(endpoint.isPublished());
	  }
}
