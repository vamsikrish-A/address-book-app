package com.bridgelabz.addressbookapp.service;
/**
 * @purpose: All the CURD operational methods to&From Repo to controller
 * @author: VamsiKrishna
 * @since: 15-12-2021
 */

import com.bridgelabz.addressbookapp.builder.AddressBookBuilder;
import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.repository.AddressBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {
    private static final String ADDRESSBOOK_ADDED_SUCCESSFULLY = "DETAILS ADDED SUCCESSFULLY";
    private static final String ADDRESSBOOK_UPDATED_SUCCESSFULLY = "DETAILS UPDATED SUCCESSFULLY";

    /**
     * @purpose: enables to inject the object dependency implicitly.
     */
    @Autowired
    private AddressBookRepo addressBookRepo;

    @Autowired
    private AddressBookBuilder addressBookBuilder;

    /**
     * @return data by find method.
     * @Purpose: used to get all the data from the repository.
     */
    public List<AddressBookEntity> getAddressBookDetails() {
        return addressBookRepo.findAll();
    }

    /**
     * @param id auto generated id to access data from repo.
     * @return data foe specific id .
     * @purpose used to get specific data by given id.
     */
    public AddressBookEntity getAddressBookDetailsById(int id) {
        return addressBookRepo.findById(id).orElseThrow(null);
    }

    /**
     * @param addressBookDto providing data objects
     * @return exceptional super message and http status codes.
     * @Purpose adding addressBook details to repo.
     */
    public ResponseDto addAddressBookDetails(AddressBookDto addressBookDto) {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity);
        addressBookRepo.save(addressBookEntity);
        return new ResponseDto(ADDRESSBOOK_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    /**
     * @param id             auto generated id to access data from repo.
     * @param addressBookDto providing data objects
     * @return exceptional super message and status code.
     * @Purpose updating existing specific data by using ID.
     */
    public ResponseDto updateAddressBookDetails(int id, AddressBookDto addressBookDto) {
        Optional<AddressBookEntity> addressBook = addressBookRepo.findById(id);
        //AddressBookEntity addressBookEntity = getAddressBookDetailsById(id);
        if (addressBook.isPresent()) {
            AddressBookEntity addressBookEntity = new AddressBookEntity();
            addressBookEntity = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity);
            addressBookRepo.save(addressBookEntity);
        }
        return new ResponseDto(ADDRESSBOOK_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }

    /**
     * @param id to identify the specific entry int repo
     * @return super message and http status code
     * @purpose deleting data from repo using id
     */
    public ResponseDto deleteAddressBookDetails(int id) {
        addressBookRepo.deleteById(id);
        return new ResponseDto("AddressBook details with ID:" + id + " Deleted Successfully",
                HttpStatus.ACCEPTED);
    }
}
