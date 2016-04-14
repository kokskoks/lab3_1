package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;

public class AddProductCommandHandlerTest {

    @Test(expected = NullPointerException.class)
    public void handle_checkForNullPointerExceptionBecauseOfNullPrivateDependencies(){

        AddProductCommandHandler addProductCommandHandler = new AddProductCommandHandler();

        AddProductCommand addProductCommand = mock(AddProductCommand.class);

        addProductCommandHandler.handle(addProductCommand);

    }


}
