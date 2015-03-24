package com.gumtree.addressbook.model;

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
}
