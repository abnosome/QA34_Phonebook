package com.phonebook.tests;

import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("marta@gm.com").setPassword( "1234Figa!"));
        app.getUser().clickOnLoginButton();
        app.getContact().clickOnAddButton();
        app.getContact().fillAddContactForm(new Contact()
                .setName("Angela")
                .setSurname("Merkeleva")
                .setPhone("1234567890")
                .setEmail("anja_merk@gm.com")
                .setAdress("Berlin, br,12")
                .setDescription("12.03.65 dr"));
        app.getContact().clickOnSaveButton();
    }
    @Test
    public void deleteContactPositiveTest(){
        int sizeBefore = app.getContact().sizeOfContacts();

        app.getContact().removeContact();
        app.getContact().pause(500);

        int sizeAfter = app.getContact().sizeOfContacts();
        Assert.assertEquals(sizeAfter,sizeBefore-1);
    }

}
