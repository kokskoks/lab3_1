package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

public class InvoiceBuilder {
	
	private ClientData clientData = ClientDataBuilder.clientData().build();
	private Id id = new Id("1234");
	
	public static InvoiceBuilder invoice(){
		return new InvoiceBuilder();
	}
	
	public InvoiceBuilder withClientData(ClientData clientData){
		this.clientData = clientData;
		return this;
	}
	
	public InvoiceBuilder withId(Id id){
		this.id = id;
		return this;
	}
	
	public Invoice build(){
		return new Invoice(id,clientData);
	}

}
