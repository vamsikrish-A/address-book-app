package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTest {
    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private AddressBookService addressBookService;

    @Test
    void givenAddressBook_WhenAddedAddDetails_ShouldReturnSuccessResponseMessage() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("AddressBook details added Successfully", HttpStatus.CREATED)),
                        HttpStatus.CREATED);
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setCity("SriKalahasti");
        addressBookDto.setState("AndhraPradesh");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");
        when(addressBookService.addAddressBookDetails(addressBookDto))
                .thenReturn(new ResponseDto("AddressBook details added Successfully",
                        HttpStatus.CREATED));
        ResponseEntity<ResponseDto> actualResponse = addressBookController.addAddressBookDetails(addressBookDto);
        Assertions.assertEquals(successResponse, actualResponse);
    }

    @Test
    void givenAddressBook_WhenWantsToGetAddressBookDetails_ShouldReturnAddressBookList() {
        when(addressBookService.getAddressBookDetails()).thenReturn(Lists.newArrayList(new AddressBookEntity()));
        ResponseEntity<List<AddressBookEntity>> actualResponse = addressBookController.getAddressBookList();
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        Assertions.assertEquals(1, actualResponse.getBody().size());
    }

    @Test
    void givenAddressBook_WhenWantsToGetAddressBookDetailsById_ShouldBeReturnTheAddressBook() {
        int id = 1;
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        when(addressBookService.getAddressBookDetailsById(id)).thenReturn(addressBookEntity);
        ResponseEntity<AddressBookEntity> actualResponse = addressBookController.getAddressBookById(id);
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    void givenAddressBook_WhenAddressBookDetailsWereUpdatedById_ShouldReturnResponse() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("AddressBook details updated Successfully",
                        HttpStatus.ACCEPTED)), HttpStatus.ACCEPTED);
        int id = 1;
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setCity("SriKalahasti");
        addressBookDto.setState("AndhraPradesh");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");
        when(addressBookService.updateAddressBookDetails(id, addressBookDto))
                .thenReturn(new ResponseDto("AddressBook details updated Successfully",
                        HttpStatus.ACCEPTED));
        ResponseEntity<ResponseDto> actualResponse = addressBookController
                .updateAddressbookDetails(id, addressBookDto);
        Assertions.assertEquals(successResponse, actualResponse);
    }

    @Test
    void givenAddressBook_WhenAddressBookDetailsDeletedById_ShouldReturnResponse() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("AddressBook data deleted by id successfully",
                        HttpStatus.ACCEPTED)), HttpStatus.ACCEPTED);
        int id = 1;
        when(addressBookService.deleteAddressBookDetails(id))
                .thenReturn(new ResponseDto("AddressBook data deleted by id successfully",
                        HttpStatus.ACCEPTED));
        ResponseEntity<ResponseDto> actualResponse = addressBookController.deleteAddressbookDetailsById(id);
        Assertions.assertEquals(successResponse, actualResponse);

    }
}
