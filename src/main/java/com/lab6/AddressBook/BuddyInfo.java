package com.lab6.AddressBook;

import jakarta.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    private String name;

    private String address;

    private String phoneNumber;

    public BuddyInfo() { }

    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "BuddyInfo[name='%s', address='%s', phoneNumber='%s']",
                name, address, phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BuddyInfo otherBuddy = (BuddyInfo) obj;
        return this.name.equals(otherBuddy.name) && this.address.equals(otherBuddy.address) && this.phoneNumber.equals(otherBuddy.phoneNumber);
    }

}
