package com.lab6.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping()
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/addressbook")
    public String addressBook(Model model) {
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        model.addAttribute("AddressBookList", addressBookList);
        return "addressbook";
    }

    @GetMapping("/addaddressbook")
    public String addAddressBook(Model model){
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        model.addAttribute("AddressId", addressBook.getId());
        return "addaddressbook";
    }

    @GetMapping(value="/showaddressbook")
    public String showAddressBook(@RequestParam Long id, Model model) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            model.addAttribute("addressBook", addressBook);
            return "showaddressbook";
        } else {
            // Handle address book not found
            return "error";
        }
    }

    @GetMapping("/addbuddy")
    public String addBuddy(@RequestParam Long id, Model model){
        BuddyInfoDTO buddyDTO = new BuddyInfoDTO(id, new BuddyInfo());
        buddyDTO.setAddressBookId(id);
        model.addAttribute("BuddyDTO", buddyDTO);
        return "addbuddyinfo";
    }

    @GetMapping("/removebuddy")
    public String removeBuddy(@RequestParam Long id, Model model){
        BuddyInfoDTO buddyDTO = new BuddyInfoDTO();
        buddyDTO.setAddressBookId(id);
        model.addAttribute("BuddyDTO", buddyDTO);
        return "removeBuddyInfo";
    }

    @PostMapping("/createbuddy")
    public ResponseEntity<?> createBuddy(@RequestBody BuddyInfoDTO buddyDTO) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(buddyDTO.getAddressBookId());
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            addressBook.addBuddy(buddyDTO.getBuddy());
            addressBookRepository.save(addressBook);

            return ResponseEntity.ok("Buddy added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address book not found");
        }
    }


    @PostMapping("/deletebuddy")
    public ResponseEntity<String> deleteBuddy(@RequestBody BuddyInfoDTO buddyDTO) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(buddyDTO.getAddressBookId());
        Optional<BuddyInfo> buddyInfoOptional = buddyInfoRepository.findById(buddyDTO.getBuddyId());

        if (addressBookOptional.isPresent() && buddyInfoOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            BuddyInfo buddyInfo = buddyInfoOptional.get();

            addressBook.getBuddies().remove(buddyInfo);
            addressBookRepository.save(addressBook);

            buddyInfoRepository.delete(buddyInfo);
            return ResponseEntity.ok("Buddy deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AddressBook or Buddy not found");
        }
    }


    private String getString(RedirectAttributes redirectAttributes, Optional<AddressBook> addressBookOptional, Optional<BuddyInfo> buddyInfoOptional) {
        if (addressBookOptional.isPresent() && buddyInfoOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            BuddyInfo buddyInfo = buddyInfoOptional.get();
            addressBook.removeBuddy(buddyInfo);
            addressBookRepository.save(addressBook);
            redirectAttributes.addAttribute("id", addressBook.getId());
            return "redirect:showaddressbook";
        } else {
            return "redirect:addressbook";
        }
    }

    @DeleteMapping(value="/")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        addressBookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
