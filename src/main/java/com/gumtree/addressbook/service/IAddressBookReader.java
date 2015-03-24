package com.gumtree.addressbook.service;

import com.gumtree.addressbook.model.Person;

import java.util.List;

public interface IAddressBookReader {
    public List<Person> read(String filePath);
    public List<Person> read(List<String> csvPersons);
}
