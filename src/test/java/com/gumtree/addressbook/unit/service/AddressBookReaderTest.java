package com.gumtree.addressbook.unit.service;

import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import com.gumtree.addressbook.service.AddressBookReader;
import com.gumtree.addressbook.service.IAddressBookReader;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddressBookReaderTest {

    private IAddressBookReader reader = new AddressBookReader();
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");


    @Test
    public void testReturnEmptyListForNotExistingAddressBookFile() {
        assertEquals("should return empty person list", 0, reader.read("/fileNotExists").size());
    }

    @Test
    public void testReturnValidEntriesInAddressBookFile() {

        List<String> csvPersons = Arrays.asList("Bill McKnight, Male, 16/03/77", "invalid, ,16/03/77");
        Person expectedPerson = new Person("Bill McKnight", Gender.MALE, formatter.parseDateTime("16/03/77"));

        assertEquals("should return valid entries person list", 1, reader.read(csvPersons).size());
        assertEquals(expectedPerson, reader.read(csvPersons).get(0));
    }


    @Test
    public void testReturnPersonsListFromValidAddressBookFile() {
        assertEquals("should return all persons list", 5, reader.read("/AddressBook").size());
    }
}