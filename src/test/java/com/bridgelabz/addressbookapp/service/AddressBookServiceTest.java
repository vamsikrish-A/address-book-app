package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.builder.AddressBookBuilder;
import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.exception.DataNotFoundException;
import com.bridgelabz.addressbookapp.repository.AddressBookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {
    @InjectMocks
    private AddressBookService addressBookService;

    @Mock
    private AddressBookRepo addressBookRepo;

    @Mock
    private AddressBookBuilder addressBookBuilder;

    @Test
    void givenAddressBook_WhenGetAddressBookDetails_ShouldReturnAddressBookList() {
        List<AddressBookEntity> addressBookEntityList = new ArrayList<>();

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setCity("SriKalahasti");
        addressBookEntity.setFirstName("Vamsi");
        addressBookEntity.setLastName("Krishna");
        addressBookEntity.setState("Andhra");
        addressBookEntity.setPhoneNumber("9876543210");
        addressBookEntity.setZipCode("654321");
        addressBookEntityList.add(addressBookEntity);

        AddressBookEntity addressBookEntity1 = new AddressBookEntity();
        addressBookEntity.setCity("SriKalahasti");
        addressBookEntity.setFirstName("Vamsi");
        addressBookEntity.setLastName("Krishna");
        addressBookEntity.setState("Andhra");
        addressBookEntity.setPhoneNumber("9876543210");
        addressBookEntity.setZipCode("654321");
        addressBookEntityList.add(addressBookEntity1);

        when(addressBookRepo.findAll()).thenReturn(addressBookEntityList);
        List<AddressBookEntity> actualList = addressBookService.getAddressBookDetails();
        Assertions.assertEquals(2, actualList.size());
        Assertions.assertEquals(addressBookEntityList, actualList);
    }

    @Test
    void givenAddressBook_WhenGetAddressBookDetailsById_ShouldThrowException() {
        int id = 1;
        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NullPointerException.class, () -> addressBookService.getAddressBookDetailsById(id));
    }

    @Test
    void givenAddressBook_WhenAddAddressBookDetails_ShouldReturnSuccessAndResponseMessage() {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setCity("Srikalahasti");
        addressBookDto.setState("Andhra");
        addressBookDto.setPhoneNumber("98765432210");
        addressBookDto.setZipCode("654321");

        when(addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity))
                .thenReturn(addressBookEntity);
        ResponseDto actualResponse = addressBookService.addAddressBookDetails(addressBookDto);
        ResponseDto expectedResponse = new ResponseDto("DETAILS ADDED SUCCESSFULLY", HttpStatus.CREATED);
        Assertions.assertEquals(expectedResponse, actualResponse);
        verify(addressBookRepo, times(1)).save(addressBookEntity);
    }

    @Test
    void givenAddressBook_WhenUpdateAddressBookDetailsById_ShouldReturnResponseMessage() {
        int id = 1;
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setCity("Srikalahasti");
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setState("Andhra");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setCity("Tirupati");
        addressBookEntity.setFirstName("Vamsi");
        addressBookEntity.setLastName("Krishna");
        addressBookEntity.setState("Andhra");
        addressBookEntity.setPhoneNumber("9632587410");
        addressBookEntity.setZipCode("632541");
        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookEntity));
        addressBookEntity.setCity(addressBookEntity.getCity());
        addressBookEntity.setPhoneNumber(addressBookEntity.getPhoneNumber());
        addressBookEntity.setZipCode(addressBookEntity.getZipCode());
        when(addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity))
                .thenReturn(addressBookEntity);
        ResponseDto actualResponse = addressBookService.updateAddressBookDetails(id, addressBookDto);
        ResponseDto expectedResponse = new ResponseDto("DETAILS UPDATED SUCCESSFULLY", HttpStatus.ACCEPTED);
        verify(addressBookRepo, times(1)).save(addressBookEntity);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenAddressBook_WhenUpdateAddressBookDetailsById_ShouldReturnException() {
        int id = 1;
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setCity("Srikalahasti");
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setState("Andhra");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NullPointerException.class, () -> addressBookService
                .updateAddressBookDetails(id, addressBookDto));
    }

    @Test
    void givenAddressBook_WhenAddressBookDeletedById_ShouldReturnResponse() {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setId(1);
        addressBookEntity.setCity("Srikalahasti");
        addressBookEntity.setFirstName("Vamsi");
        addressBookEntity.setLastName("Krishna");
        addressBookEntity.setState("Andhra");
        addressBookEntity.setPhoneNumber("9876543210");
        addressBookEntity.setZipCode("654321");

        when(addressBookRepo.findById(addressBookEntity.getId())).thenReturn(Optional.of(addressBookEntity));
        ResponseDto actualResponse = addressBookService.deleteAddressBookDetails(addressBookEntity.getId());
        ResponseDto expectedResponse = new ResponseDto
                ("AddressBook details with ID:" + addressBookEntity.getId() + " Deleted Successfully",
                        HttpStatus.ACCEPTED);
    }

    @Test
    void givenAddressBook_WhenAddressBookDeletedById_ShouldThrowException() {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setId(1);
        addressBookEntity.setCity("Srikalahasti");
        addressBookEntity.setFirstName("Vamsi");
        addressBookEntity.setLastName("Krishna");
        addressBookEntity.setState("Andhra");
        addressBookEntity.setPhoneNumber("9876543210");
        addressBookEntity.setZipCode("654321");
        when(addressBookRepo.findById(addressBookEntity.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NullPointerException.class, () -> addressBookService
                .deleteAddressBookDetails(addressBookEntity.getId()));

    }
}
