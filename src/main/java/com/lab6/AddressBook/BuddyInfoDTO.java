package com.lab6.AddressBook;


public class BuddyInfoDTO {
    private Long addressBookId;
    private Long buddyId;
    private BuddyInfo buddy;

    public BuddyInfoDTO(Long addressBookId, BuddyInfo buddy) {
        this.addressBookId = addressBookId;;
        this.buddy = buddy;
        this.buddyId = buddy != null ? buddy.getId() : null;
    }

    public BuddyInfoDTO() {
        this.buddy = new BuddyInfo();
    }

    public Long getAddressBookId() {
        return addressBookId;
    }

    public Long getBuddyId() {
        return buddyId;
    }

    public BuddyInfo getBuddy() {
        return buddy;
    }

    public String getBuddyName() {
        return buddy != null ? buddy.getName() : null;
    }

    public String getBuddyAddress() {
        return buddy != null ? buddy.getAddress() : null;
    }

    public String getBuddyPhoneNumber() {
        return buddy != null ? buddy.getPhoneNumber() : null;
    }

    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    public void setBuddyId(Long buddyId) {
        this.buddyId = buddyId;
    }

    public void setBuddy(BuddyInfo buddy) {
        this.buddy = buddy;
    }
}
