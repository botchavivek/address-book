package com.gumtree.addressbook.unit.model;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class AddressBookTest {

    private DateTime dob = DateTime.now();

    private Person oldestPerson = new Person("Ai", Gender.MALE, dob);
    private Person anotherPerson = new Person("Jim", Gender.MALE, dob);
    private Person female1 = new Person("Leena", Gender.FEMALE, dob.plusYears(1));
    private Person male1 = new Person("Vivek Botcha", Gender.MALE, dob.plusYears(2));
    private Person male2 = new Person("Paul", Gender.MALE, dob.plusYears(3));



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
}
