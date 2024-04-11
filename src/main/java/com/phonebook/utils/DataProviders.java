package com.phonebook.utils;

import com.phonebook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> addNewContactFromCsvFile() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));

        String line = reader.readLine();
        while (line != null) {

            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0]).setSurname(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAdress(split[4]).setDescription(split[5])});
            line= reader.readLine();
        }
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> addNewContact(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Maria","Kanoe","1233456764","knoe@gm.com","Bavaria","12.03.89"});
        list.add(new Object[]{"Anna","Müller","9876543261","anna@example.com","Berlin","25.08.95"});
        list.add(new Object[]{"Max","Schmidt","9876543961","max@example.com","Hamburg","07.11.87"});

        return  list.iterator();
    }
}
