package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class RequestItemBuilder {
	private ProductData productData = mock(ProductData.class); //ProductData constructor is not visible from here

	private int quantity = 1;

	private Money totalCost = new Money(1d);
	
	private RequestItemBuilder(){
		when(productData.getType()).thenReturn(ProductType.STANDARD);
	}
	
	public static RequestItemBuilder requestItem(){
		return new RequestItemBuilder();
	}
	
	public RequestItemBuilder withProductType(ProductType productType){
		when(productData.getType()).thenReturn(productType);
		return this;
	}
	
	public RequestItemBuilder withQuantity(int quantity){
		this.quantity = quantity;
		return this;
	}
	
	public RequestItemBuilder withTotalCost(Money money){
		this.totalCost = money;
		return this;
	}
	
	public RequestItem build(){
		return new RequestItem(productData, quantity, totalCost);
	}
	
	

}
