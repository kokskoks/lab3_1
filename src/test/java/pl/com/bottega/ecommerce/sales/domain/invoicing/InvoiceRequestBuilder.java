package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;

public class InvoiceRequestBuilder {
	
	private ClientData clientData = ClientDataBuilder.clientData().build();
	
	public static InvoiceRequestBuilder invoiceRequest(){
		return new InvoiceRequestBuilder();
	}
	
	public InvoiceRequestBuilder withClientData(ClientData clientData){
		this.clientData = clientData;
		return this;
	}
	
	public InvoiceRequest build(){
		return new InvoiceRequest(clientData);
	}

}
