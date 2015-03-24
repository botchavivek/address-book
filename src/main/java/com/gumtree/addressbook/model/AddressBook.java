package com.gumtree.addressbook.model;

import org.joda.time.Days;

import java.util.List;

public class AddressBook {
    private List<Person> persons;

    public AddressBook(List<Person> persons) {
        if(persons == null) throw new IllegalArgumentException("persons as null argument not allowed");

        this.persons = persons;
    }

    public int getMaleCount() {
        if(persons.isEmpty()) return 0;

        int count = 0;
        for (Person person : persons) {
            if (person.isMale()) {
                count++;
            }
        }
        return count;
    }

    public Person findOldestPerson() {
        Person oldest = null;
        for (Person person : persons) {
            if (oldest == null || person.getDob().isBefore(oldest.getDob())) {
                oldest = person;
            }
        }
        return oldest;
    }

    public Integer findAgeDifferenceInDays(String name1, String name2) {
        Person person1 = findPersonByName(name1);
        Person person2 = findPersonByName(name2);

        if (person1 == null  || person2 == null) {
            return null;
        }

        return Days.daysBetween(person2.getDob(), person1.getDob()).getDays();
    }

    private Person findPersonByName(String name) {
        for (Person person : persons) {
            if(person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AddressBook)) return false;

        return persons.equals(((AddressBook) other).persons);

    }
}
