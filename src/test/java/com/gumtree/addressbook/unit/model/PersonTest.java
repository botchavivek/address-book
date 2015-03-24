package com.gumtree.addressbook.unit.model;

import com.gumtree.addressbook.exceptions.InvalidPersonException;
import com.gumtree.addressbook.model.Person;
import org.joda.time.DateTime;
import org.junit.Test;

import static com.gumtree.addressbook.model.Gender.FEMALE;
import static com.gumtree.addressbook.model.Gender.MALE;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    private DateTime personDob = DateTime.now();
    private String personName = "vivek";

    @Test(expected = InvalidPersonException.class)
    public void testPersonNameShouldNotBeNull() {
        new Person(null, MALE, personDob);
    }

    @Test(expected = InvalidPersonException.class)
    public void testPersonNameShouldNotBeEmpty() {
        new Person("", MALE, personDob);
    }

    @Test(expected = InvalidPersonException.class)
    public void testPersonGenderShouldNotBeNull() {
        new Person(personName, null, personDob);
    }


    @Test(expected = InvalidPersonException.class)
    public void testPersonDobShouldNotBeNull() {
        new Person(personName, MALE, null);
    }

    @Test
    public void testPersonIsMale() {
        Person person = new Person("vivek", MALE, DateTime.now());

        assertEquals(true, person.isMale());
        assertEquals(false, person.isFemale());
    }

    @Test
    public void testPersonIsFemale() {
        Person person = new Person("Veni", FEMALE, DateTime.now());

        assertEquals(true, person.isFemale());
        assertEquals(false, person.isMale());
    }
}
