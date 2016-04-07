package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Invoice;


public class BookKeeperTest {

	@Test
	public void issuance_InvoiceWithOneItem_resultTest() {
		InvoiceFactory invoiceFactoryStub = mock(InvoiceFactory.class);
		Invoice invoiceStub = mock(Invoice.class);
		
		
		when(invoiceFactoryStub.create(any(ClientData.class))).thenReturn(invoiceStub);
		
		BookKeeper bookKeeper = new BookKeeper(invoiceFactoryStub);
		
		InvoiceRequest invoiceRequest = mock(InvoiceRequest.class);
		
		RequestItem requestItem = mock(RequestItem.class);
		
		when(requestItem.getProductData().getType()).thenReturn(ProductType.DRUG);
		
		
		
		when(invoiceRequest.getItems()).thenReturn(Arrays.asList(requestItem));
		
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		
		Tax taxStub = mock(Tax.class);
		
		when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(taxStub);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(invoice.getItems().size(), Matchers.is(1));
		
	}
	
	@Test
	public void issuance_InvoiceWithTwoItems_behaviorTest() {
		fail("Not yet implemented");
	}

}
