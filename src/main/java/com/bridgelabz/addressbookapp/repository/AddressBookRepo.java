package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepo extends JpaRepository<AddressBookEntity, Integer> {
    void deleteById(AddressBookEntity addressBookDetailsById);
}
