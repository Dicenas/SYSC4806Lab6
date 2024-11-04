package com.lab6.AddressBook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BuddyAddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyAddressBookApplication.class, args);
	}

	@Bean
	public CommandLineRunner addressable(AddressBookRepository repository) {
		return (args) -> {
			AddressBook buddies = new AddressBook();
			List<BuddyInfo> buddyInfoList = new ArrayList<>();

			buddyInfoList.add(new BuddyInfo("Donald Enda", "3432 Penny Island Dr.", "123-555-4321"));
			buddyInfoList.add(new BuddyInfo("Eden Green", "420 Big Tree Rd.", "553-040-1134"));
			buddyInfoList.add(new BuddyInfo("Elmo", "123 Sesame St.", "123-456-7890"));
			buddyInfoList.add(new BuddyInfo("Barry Benson", "1802 E 65th St", "999-999-9999"));
			buddies.setBuddies(buddyInfoList);
			repository.save(buddies);
		};
	}

}
