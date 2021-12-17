package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.builder.AddressBookBuilder;
import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.repository.AddressBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {
    private static final String ADDRESSBOOK_ADDED_SUCCESSFULLY = "DETAILS ADDED SUCCESSFULLY";
    private static final String ADDRESSBOOK_UPDATED_SUCCESSFULLY = "DETAILS UPDATED SUCCESSFULLY";

    @Autowired
    private AddressBookRepo addressBookRepo;

    @Autowired
    private AddressBookBuilder addressBookBuilder;

    public List<AddressBookEntity> getAddressBookDetails() {
        return addressBookRepo.findAll();
    }

    public AddressBookEntity getAddressBookDetailsById(int id) {
        return addressBookRepo.findById(id).orElseThrow(null);
    }

    public ResponseDto addAddressBookDetails(AddressBookDto addressBookDto) {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity);
        addressBookRepo.save(addressBookEntity);
        return new ResponseDto(ADDRESSBOOK_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    public ResponseDto updateAddressBookDetails(int id, AddressBookDto addressBookDto) {
        AddressBookEntity addressBookEntity = getAddressBookDetailsById(id);
        addressBookEntity = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity);
        addressBookRepo.save(addressBookEntity);
        return new ResponseDto(ADDRESSBOOK_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }

    public ResponseDto deleteAddressBookDetails(int id) {
        addressBookRepo.deleteById(getAddressBookDetailsById(id));
        return new ResponseDto("AddressBook details with ID:" +id+" Deleted Successfully", HttpStatus.ACCEPTED);
    }
}
