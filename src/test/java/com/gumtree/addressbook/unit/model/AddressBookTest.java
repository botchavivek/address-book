package com.gumtree.addressbook.unit.model;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.gumtree.addressbook.model.Gender.FEMALE;
import static com.gumtree.addressbook.model.Gender.MALE;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class AddressBookTest {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");

    private DateTime dob = formatter.parseDateTime("01/01/1983");

    private Person oldestPerson = new Person("Ai", MALE, dob);
    private Person anotherPerson = new Person("Jim", MALE, dob);
    private Person female1 = new Person("Leena", FEMALE, dob.plusYears(1));
    private Person male1 = new Person("Vivek Botcha", MALE, dob.plusYears(2));
    private Person male2 = new Person("Paul", MALE, dob.plusYears(3));


    @Test
    public void testZeroMalesInEmptyAddressBook() {
        //Given
        AddressBook addressBook = new AddressBook(Collections.<Person>emptyList());

        //When
        int maleCount = addressBook.getMaleCount();

        //then
        assertEquals("Address book should contains 0 males", 0, maleCount);
    }

    @Test
    public void testZeroMalesWhenOnlyFemalePersonsInAddressBook() {

        //Given
        List<Person> females = asList(female1);
        AddressBook addressBook = new AddressBook(females);

        //When
        int maleCount = addressBook.getMaleCount();

        //then
        assertEquals("Address book should contains 0 males", 0, maleCount);
    }


    @Test
    public void testTwoMalesInAddressBook() {
        //Given
        List<Person> persons = asList(male1, female1, male2);

        AddressBook addressBook = new AddressBook(persons);

        //When
        int maleCount = addressBook.getMaleCount();

        //then
        assertEquals("Address book should contains 2 males", 2, maleCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddressBookNotCreatedWithNullPersons() {
        //Given & When & Then
        new AddressBook(null);
    }

    @Test
    public void testAddressEquals() {
        AddressBook book1 = new AddressBook(Arrays.asList(male1, male2));
        AddressBook book2 = new AddressBook(Arrays.asList(male1, male2));
        AddressBook book3 = new AddressBook(Arrays.asList(male1, female1));
        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
    }

    @Test
    public void testFindOldestPersonwithEmptyAddressBook() {

        AddressBook addressBook = new AddressBook(Collections.EMPTY_LIST);

        Person oldestPerson = addressBook.findOldestPerson();
        assertNull("Oldest person is null", oldestPerson);
    }

    @Test
    public void testFindOldestPersonOnNonEmptyAddressBook() {

        AddressBook addressBook = new AddressBook(Arrays.asList(male1, oldestPerson, female1, male2));

        Person actualPerson = addressBook.findOldestPerson();

        assertEquals(oldestPerson, actualPerson);
    }

    @Test
    public void testFindFirstOldestPersonWhenMultipleOldestPersonsExists() {

        AddressBook addressBook = new AddressBook(Arrays.asList(male1, anotherPerson, oldestPerson, female1, male2));

        Person actualPerson = addressBook.findOldestPerson();

        assertEquals(anotherPerson, actualPerson);
    }

    @Test
    public void testReturnAgeDifferenceAsNullWhenAddressBookIsEmpty() {
        AddressBook addressBook = new AddressBook(Collections.EMPTY_LIST);

        Integer ageDifferenceInDays = addressBook.findAgeDifferenceInDays(male1.getName(), male2.getName());
        assertNull("Age difference is null", ageDifferenceInDays);
    }

    @Test
    public void testReturnAgeDifferenceAsNullWhenPersonNotExistsInAddressBook() {
        AddressBook addressBook = new AddressBook(Arrays.asList(male1, oldestPerson, female1, male2));
        Person personNotExists = new Person("Not_Exists", MALE, dob);
        Integer ageDifferenceInDays = addressBook.findAgeDifferenceInDays(personNotExists.getName(), male2.getName());
        assertNull("Age difference is null", ageDifferenceInDays);
    }

    @Test
    public void testReturnAgeDifferenceInDays() {
        AddressBook addressBook = new AddressBook(Arrays.asList(male1, oldestPerson, female1, male2));

        assertEquals("Age difference is 365", 365, addressBook.findAgeDifferenceInDays(male2.getName(), male1.getName()).intValue());
        assertEquals("Age difference is -365", -365, addressBook.findAgeDifferenceInDays(male1.getName(), male2.getName()).intValue());
    }
}
