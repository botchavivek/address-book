package com.gumtree.addressbook;


import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.service.AddressBookFactory;
import com.gumtree.addressbook.service.AddressBookReader;

public class Main {

    public static void main(String[] args) throws Exception {
        AddressBook addressBook = AddressBookFactory.createAddressBook(new AddressBookReader());

        // 1. How many males are in the address book?
        System.out.println("Males count: " + addressBook.getMaleCount());
        // 2. Who is the oldest person in the address book?
        System.out.println("Oldest person: " + addressBook.findOldestPerson());
        // 3. How many days older is Bill than Paul?
        System.out.println("How many days older is Bill than Paul?:"
                + addressBook.findAgeDifferenceInDays("Paul Robinson", "Bill McKnight"));
    }
}
