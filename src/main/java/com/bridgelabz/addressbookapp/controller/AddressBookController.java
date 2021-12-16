package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @PostMapping(value = "/details")
    public ResponseEntity<ResponseDto> addAddressBookDetails(@Valid @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity(addressBookService.addAddressBookDetails(addressBookDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/details")
    public ResponseEntity<List<AddressBookEntity>> getAddressBookList() {
        return new ResponseEntity(addressBookService.getAddressBookDetails(), HttpStatus.OK);
    }

    @GetMapping(value = "/details/{Id}")
    public ResponseEntity<AddressBookEntity> getAddressBookById(@PathVariable int id) {
        return new ResponseEntity(addressBookService.getAddressBookDetailsById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/details/{Id}")
    public ResponseEntity<ResponseDto> updateAddressbookDetails(@PathVariable int id,
                                                                @Valid @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity(addressBookService.updateAddressBookDetails(id, addressBookDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/details/{Id}")
    public ResponseEntity<ResponseDto> deleteAddressbookDetailsById(@PathVariable int id) {
        return new ResponseEntity(addressBookService.deleteAddressBookDetails(id), HttpStatus.ACCEPTED);
    }

    
}
