package com.github.ibykhalov.simplespring.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String thirdName;
    @Basic
    private Date birthDate;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' +
               ", thirdName='" + thirdName + '\'' + ", birthDate=" + birthDate + '}';
    }
}
