package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;


public class BookKeeperTest {

	@Test
	public void issuance_InvoiceWithOneItem_resultTest() {
		InvoiceFactory invoiceFactoryStub = mock(InvoiceFactory.class);

		Invoice invoiceToReturn = createSampleInvoice();

		when(invoiceFactoryStub.create(any(ClientData.class))).thenReturn(invoiceToReturn);
		
		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);

		InvoiceRequest invoiceRequest = new InvoiceRequest(createSampleClientData());
		invoiceRequest.add(createSampleRequestItem());
		
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		
		Tax tax = createSampleTax();
		
		when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(invoice.getItems().size(), Matchers.is(1));
		
	}

	@Test
	public void issuance_InvoiceWithTwoItems_behaviorTest() {
		fail("Not yet implemented");
	}

	private Tax createSampleTax() {
		return new Tax(Money.ZERO,"sample tax");
	}


	private Invoice createSampleInvoice(){
		Invoice invoice = new Invoice(Id.generate(), createSampleClientData());
		return invoice;
	}

	private RequestItem createSampleRequestItem(){
		ProductData productData = mock(ProductData.class); //ProductData constructor is not visible from here
		when(productData.getType()).thenReturn(ProductType.STANDARD);
		RequestItem item = new RequestItem(productData,  1, Money.ZERO);
		return item;
	}

	private ClientData createSampleClientData(){
		return  new ClientData(Id.generate(), "Test");
	}

}
