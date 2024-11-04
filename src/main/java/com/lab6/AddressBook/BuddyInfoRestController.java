package com.lab6.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/buddyinfo/api")
public class BuddyInfoRestController {
    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddyInfo) {
        return buddyInfoRepository.save(buddyInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuddyInfo> getBuddyInfo(@PathVariable Long id) {
        Optional<BuddyInfo> buddyInfo = buddyInfoRepository.findById(id);
        return buddyInfo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteBuddyInfo(@PathVariable Long id) {
        buddyInfoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
