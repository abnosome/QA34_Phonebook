package com.phonebook.tests;

import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("marta@gm.com").setPassword( "1234Figa!"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest(){
        app.getContact().clickOnAddButton();
        app.getContact().fillAddContactForm(new Contact()
                .setName("Angela")
                .setSurname("Merkeleva")
                .setPhone("1234567890")
                .setEmail("anja_merk@gm.com")
                .setAdress("Berlin, br,12")
                .setDescription("12.03.65 dr"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated("Angela"));
    }

    @AfterMethod
    public void postCondition(){
        app.getContact().removeContact();
    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastname, String phone,String email, String address,String description){
        app.getContact().clickOnAddButton();
        app.getContact().fillAddContactForm(new Contact()
                .setName(name)
                .setSurname(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAdress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));
    }



    @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithCsvFile(Contact contact){
        app.getContact().clickOnAddButton();
        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(contact.getName()));
    }

}
