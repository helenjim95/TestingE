package de.tum.in.ase.eist;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class AccountTest {

    @TestSubject
    Account account = new Account("account");

    @Mock(fieldName = "checkoutMethodMock1")
    CheckoutMethod checkoutMethodMock1;

    @Mock(fieldName = "checkoutMethodMock2")
    CheckoutMethod checkoutMethodMock2;

    @Mock(fieldName = "checkoutMethodMock3")
    CheckoutMethod checkoutMethodMock3;

    @Mock(fieldName = "checkoutMethodMock4")
    CheckoutMethod checkoutMethodMock4;

    @Test
    public void testCheckoutMethod() {
        Item item1 = new Item(10);
        expect(checkoutMethodMock1.pay(item1)).andReturn(false);
        expect(checkoutMethodMock2.pay(item1)).andReturn(true);
        expect(checkoutMethodMock3.pay(item1)).andReturn(true);
        replay(checkoutMethodMock1);
        replay(checkoutMethodMock2);
        replay(checkoutMethodMock3);
        List<CheckoutMethod> checkoutMethodsList = new ArrayList<>();
        checkoutMethodsList.add(checkoutMethodMock1);
        checkoutMethodsList.add(checkoutMethodMock2);
        checkoutMethodsList.add(checkoutMethodMock3);
        account.setCheckoutMethods(checkoutMethodsList);
        assertTrue(account.checkoutFor(item1));
        verify(checkoutMethodMock1);
        verify(checkoutMethodMock2);
        verify(checkoutMethodMock3);
    }


}
