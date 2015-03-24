package com.gumtree.addressbook.model;

import com.gumtree.addressbook.exceptions.InvalidPersonException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import static com.gumtree.addressbook.model.Gender.FEMALE;
import static com.gumtree.addressbook.model.Gender.MALE;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Person {

    private String name;
    private Gender gender;
    private DateTime dob;

    public Person(String name, Gender gender, DateTime dob) {

        if (isEmpty(name) || gender == null || dob == null) {
            throw new InvalidPersonException();
        }

        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public DateTime getDob() {
        return dob;
    }

    public void setDob(DateTime dob) {
        this.dob = dob;
    }

    public boolean isMale() {
        return gender == MALE;
    }

    public boolean isFemale() {
        return gender == FEMALE;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person){
            final Person other = (Person) obj;
            return new EqualsBuilder()
                    .append(name, other.name)
                    .append(gender, other.gender)
                    .append(dob, other.dob)
                    .isEquals();
        } else{
            return false;
        }

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("gender", gender)
                .append("dob", dob).toString();
    }
}