package com.lab6.AddressBook;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<BuddyInfo> buddies;

    public AddressBook() {
        this.buddies = new ArrayList<>();
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public void addBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || buddies.contains(aBuddy))
            return;
        this.buddies.add(aBuddy);
    }

    public void removeBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || !buddies.contains(aBuddy))
            return;
        this.buddies.remove(aBuddy);
    }

    public int size() {
        return buddies.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AddressBook addressBook))
            return false;
        return this.size() == addressBook.size();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public boolean removeBuddyById(Long buddyId) {
        if (buddyId == null) return false;

        BuddyInfo buddyToRemove = null;
        for (BuddyInfo buddy : buddies) {
            if (buddyId.equals(buddy.getId())) {
                buddyToRemove = buddy;
                break;
            }
        }

        if (buddyToRemove != null) {
            buddies.remove(buddyToRemove);
            return true;
        }

        return false;
    }

}
