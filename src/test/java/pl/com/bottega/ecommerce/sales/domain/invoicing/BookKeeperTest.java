package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;


public class BookKeeperTest {
	InvoiceFactory invoiceFactoryStub;
	Invoice invoiceToReturn;
	InvoiceRequest invoiceRequest;
	TaxPolicy taxPolicy;
	Tax tax;


	@Before
	public void setUp(){
		invoiceFactoryStub = mock(InvoiceFactory.class);

		invoiceToReturn = createSampleInvoice();

		when(invoiceFactoryStub.create(any(ClientData.class))).thenReturn(invoiceToReturn);

		ClientData clientData = ClientDataBuilder.clientData().build();
		invoiceRequest = new InvoiceRequest(clientData);

		taxPolicy = mock(TaxPolicy.class);

		tax = TaxBuilder.tax().build();

		when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);

	}

	@Test
	public void issuance_InvoiceWithOneItem_resultTest() {

		
		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);

		invoiceRequest.add(createSampleRequestItem());

		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(invoice.getItems().size(), Matchers.is(1));
		
	}

	@Test
	public void issuance_InvoiceWithTwoItems_behaviourTest() {
		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);

		invoiceRequest.add(createSampleRequestItem());
		invoiceRequest.add(createSampleRequestItem());

		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

		verify(taxPolicy, times(2)).calculateTax(any(ProductType.class), any(Money.class));
	}

	@Test
	public void issuance_InvoiceRequestNoItems_resultTest() {

		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);

		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

		assertThat(invoice.getItems().size(), Matchers.is(0));

	}

	@Test
	public void issuance_checkIfInvoiceIsCreatedOnlyOnce_behaviourTest(){
		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);

		invoiceRequest.add(createSampleRequestItem());
		invoiceRequest.add(createSampleRequestItem());

		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

		verify(invoiceFactoryStub, times(1)).create(any(ClientData.class));
	}



	private Invoice createSampleInvoice(){
		ClientData clientData = ClientDataBuilder.clientData().build();
		Invoice invoice = new Invoice(Id.generate(), clientData);
		return invoice;
	}

	private RequestItem createSampleRequestItem(){
		ProductData productData = mock(ProductData.class); //ProductData constructor is not visible from here
		when(productData.getType()).thenReturn(ProductType.STANDARD);
		RequestItem item = new RequestItem(productData,  1, Money.ZERO);
		return item;
	}


}
