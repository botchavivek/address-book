package com.gumtree.addressbook.unit.service;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import com.gumtree.addressbook.service.AddressBookFactory;
import com.gumtree.addressbook.service.IAddressBookReader;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookFactoryTest {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    IAddressBookReader reader;

    @Test
    public void testShouldReturnAddressBook() {
        List<Person> persons = Arrays.asList(new Person("vivek", Gender.MALE, DateTime.now()));
        when(reader.read("/AddressBook")).thenReturn(persons);

        Assert.assertEquals(new AddressBook(persons), AddressBookFactory.createAddressBook(reader));

    }

}