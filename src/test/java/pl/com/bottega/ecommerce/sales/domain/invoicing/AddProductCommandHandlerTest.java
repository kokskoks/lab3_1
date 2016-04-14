package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;

public class AddProductCommandHandlerTest {

    AddProductCommand addProductCommand;

    @Before
    public void setUp(){
        addProductCommand = mock(AddProductCommand.class);

        when(addProductCommand.getOrderId()).thenReturn(Id.generate());
    }


    @Test(expected = NullPointerException.class)
    public void handle_checkForNullPointerExceptionBecauseOfNullPrivateDependencies(){

        AddProductCommandHandler addProductCommandHandler = new AddProductCommandHandler();

        addProductCommandHandler.handle(addProductCommand);

    }

    @Test
    public void handle_checkForGetOrderIdCall(){

        AddProductCommandHandler addProductCommandHandler = new AddProductCommandHandler();

        try {
            addProductCommandHandler.handle(addProductCommand);
        } catch(NullPointerException e){
            //this is expected
        }

        verify(addProductCommand, times(1)).getOrderId();

    }

}
