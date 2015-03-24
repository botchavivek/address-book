package com.gumtree.addressbook.service;

import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBookReader implements IAddressBookReader {
    final static Logger logger = Logger.getLogger(AddressBookReader.class);

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");

    public List<Person> read(String filePath) {
        return read(readLinesFromFile(filePath));
    }

    public List<Person> read(List<String> csvPersons) {

        List<Person> persons = new ArrayList<Person>();

        for (String line: csvPersons) {
            Person person = transformToPerson(line);
            if(person != null) {
                persons.add(person);
            }
        }

        return persons;
    }

    private Person transformToPerson(String line) {
        try {
            String[] split = line.split(",");
            String name = split[0].trim();
            Gender gender = Gender.valueOf(split[1].trim().toUpperCase());

            DateTime dob = formatter.parseDateTime(split[2].trim());
            return new Person(name, gender, dob);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return null;
    }

    private List<String> readLinesFromFile(String filePath) {
        try {
            return FileUtils.readLines(new File(this.getClass().getResource(filePath).getPath()));
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return Collections.emptyList();
    }
}
