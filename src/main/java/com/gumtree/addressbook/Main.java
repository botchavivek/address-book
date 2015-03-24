package com.gumtree.addressbook;


import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.service.AddressBookFactory;
import com.gumtree.addressbook.service.AddressBookReader;

public class Main {

    public static void main(String[] args) throws Exception {
        AddressBook addressBook = AddressBookFactory.createAddressBook(new AddressBookReader());

        // 1. How many males are in the address book?
        System.out.println("Males count: " + addressBook.getMaleCount());

    }
}
