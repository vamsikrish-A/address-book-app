package com.bridgelabz.addressbookapp.controller;
/**
 * @Purpose: used to operate all the RESTful APIs Http methods by using RestController
 * @author: Vamsi Krishna
 * @since: 15/12/2021
 */

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
    /**
     * @purpose: enables to inject the object dependency implicitly.
     */
    @Autowired
    private AddressBookService addressBookService;

    /**
     * @param addressBookDto passing data with multiple attributes
     * @return http method return body and status code.
     * @purpose: @PostMapping annotation maps HTTP POST requests onto specific handler methods.
     */
    @PostMapping(value = "/details")
    public ResponseEntity<ResponseDto> addAddressBookDetails(@Valid @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity(addressBookService.addAddressBookDetails(addressBookDto), HttpStatus.CREATED);
    }

    /**
     * @return http method return body and status codes.
     * @purpose: @GetMapping annotation maps HTTP GET requests onto specific handler methods.
     */
    @GetMapping(value = "/details")
    public ResponseEntity<List<AddressBookEntity>> getAddressBookList() {
        return new ResponseEntity(addressBookService.getAddressBookDetails(), HttpStatus.OK);
    }

    /**
     * @param id gets data by find auto generated ID form repo.
     * @return http method body and status code.
     * @purpose: @GetMapping annotation maps HTTP GET requests onto specific handler methods.
     */
    @GetMapping(value = "/details/{id}")
    public ResponseEntity<AddressBookEntity> getAddressBookById(@PathVariable int id) {
        return new ResponseEntity(addressBookService.getAddressBookDetailsById(id), HttpStatus.OK);
    }

    /**
     * @param id             get data from repo by using auto generated Id
     * @param addressBookDto passing data with multiple attributes
     * @return http method body and status code.
     * @purpose: @PutMapping annotation maps HTTP PUT requests onto specific handler methods.
     */
    @PutMapping(value = "/details/{id}")
    public ResponseEntity<ResponseDto> updateAddressbookDetails(@PathVariable int id,
                                                                @Valid @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity(addressBookService.updateAddressBookDetails(id, addressBookDto), HttpStatus.ACCEPTED);
    }

    /**
     * @param id used to collect the data from repo
     * @return http method body and status code.
     * @purpose: @DeleteMapping annotation maps HTTP DELETE requests onto specific handler methods.
     */
    @DeleteMapping(value = "/details/{id}")
    public ResponseEntity<ResponseDto> deleteAddressbookDetailsById(@PathVariable int id) {
        return new ResponseEntity(addressBookService.deleteAddressBookDetails(id), HttpStatus.ACCEPTED);
    }


}
