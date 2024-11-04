package com.lab6.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/showaddressbook/api")
public class AddressBookRestController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<AddressBook> getAddressBook(@PathVariable Long id) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        return addressBook.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        addressBookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
