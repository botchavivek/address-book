package com.gumtree.addressbook.service;

import com.gumtree.addressbook.model.AddressBook;

public class AddressBookFactory {
    private static String addressBookFilePath = "/AddressBook";  // should be come from properties file
    public static AddressBook createAddressBook(IAddressBookReader reader) {
        return new AddressBook(reader.read(addressBookFilePath));
    }
}
