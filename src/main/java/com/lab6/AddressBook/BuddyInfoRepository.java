package com.lab6.AddressBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    @NonNull
    List<BuddyInfo> findAll();

    Optional<BuddyInfo> findByName(String name);

}
