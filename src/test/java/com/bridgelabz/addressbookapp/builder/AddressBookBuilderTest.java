package com.bridgelabz.addressbookapp.builder;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressBookBuilderTest {
    @InjectMocks
    private AddressBookBuilder addressBookBuilder;

    @Test
    void buildEntityTest() {
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setCity("SriKalahasti");
        addressBookDto.setState("Andhrapradesh");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity = addressBookBuilder.buildAddressBookEntity(addressBookDto, addressBookEntity);
        Assertions.assertEquals(addressBookDto.getFirstName(), addressBookEntity.getFirstName());
        Assertions.assertEquals(addressBookDto.getLastName(), addressBookEntity.getLastName());
        Assertions.assertEquals(addressBookDto.getCity(), addressBookEntity.getCity());
        Assertions.assertEquals(addressBookDto.getState(), addressBookEntity.getState());
        Assertions.assertEquals(addressBookDto.getPhoneNumber(), addressBookEntity.getPhoneNumber());
        Assertions.assertEquals(addressBookDto.getZipCode(), addressBookEntity.getZipCode());
    }
}
