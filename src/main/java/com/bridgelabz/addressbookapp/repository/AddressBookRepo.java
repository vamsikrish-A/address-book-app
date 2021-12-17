package com.bridgelabz.addressbookapp.repository;
/**
 * @purpose: repository interface dialects the Databases by using JpaRepository
 * @author: VamsiKrishna
 * @since: 15-12-2021
 */

import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepo extends JpaRepository<AddressBookEntity, Integer> {
    void deleteById(AddressBookEntity addressBookDetailsById);
}
