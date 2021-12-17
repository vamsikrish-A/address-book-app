package com.bridgelabz.addressbookapp.integration;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
@ActiveProfiles("test")
public class AddressBookIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookService;

    @Test
    void addAddressbookDetailsTest() throws Exception {
        when(addressBookService.addAddressBookDetails(any())).thenReturn(new ResponseDto("DETAILS ADDED SUCCESSFULLY", HttpStatus.CREATED));
        mockMvc.perform(MockMvcRequestBuilders.post("/addressbook/details")
                        .content("\"{\\\"firstName\\\": \\\"Vamsi\\\", +" +
                                "\\\"lastName\\\": \\\"Krishna\\\", \\\"city\\\": \\\"SriKalahasti\\\", +" +
                                "\\\"state\\\": \\\"AndhraPradesh\\\", \\\"phoneNumber\\\": \\\"9876543210\\\", +" +
                                "\\\"zipCode\\\": \\\"654321\\\"}\"")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAddressBookDetailsTest() throws Exception {
        when(addressBookService.getAddressBookDetails()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/addressbook/details"))
                .andExpect(status().isOk());
    }

    @Test
    void getAddressBookDetailsByIdTest() throws Exception {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        when(addressBookService.getAddressBookDetailsById(1)).thenReturn(addressBookEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/addressbook/details/1"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void updateAddressBookDetailsByIdTest() throws Exception {
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setCity("Srikalahasti");
        addressBookDto.setFirstName("Vamsi");
        addressBookDto.setLastName("Krishna");
        addressBookDto.setState("AndhraPradesh");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZipCode("654321");
        int id = 1;

        when(addressBookService.updateAddressBookDetails(id, addressBookDto))
                .thenReturn(new ResponseDto("DETAILS UPDATED SUCCESSFULLY", HttpStatus.ACCEPTED));
        mockMvc.perform(MockMvcRequestBuilders.put("/addressbook/details/{Id}", id)
                        .content("{\"id\": 1, \"city\": \"SriKalahasti\",\"firstName\": \"Vamsi\", \"lastName\": \"Krishna\", \"state\": \"AndhraPradesh\", \"phoneNumber\": \"9876543210\", \"zipCode\": \"654321\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

    }

    @Test
    void deleteAddressBookDetailsTest() throws Exception {
        when(addressBookService.deleteAddressBookDetails(1)).thenReturn(new ResponseDto("AddressBook details with ID:\" +id+\" Deleted Successfully", HttpStatus.ACCEPTED));
        mockMvc.perform(MockMvcRequestBuilders.delete("/addressbook/details/{Id}", 1))
                .andExpect(status().is5xxServerError());
    }
}
