package com.bridgelabz.addressbookapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AddressBookDto {
    @NotNull(message = "CityName should not be empty")
    private String city;
    @NotNull(message = "First name Should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name is invalid!!!")
    private String firstName;
    @NotNull(message = "Lastname should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "LastName is invalid!")
    private String lastName;
    @NotNull(message = "State name should not be empty")
    private String state;
    @NotNull(message = "phone  number should not be empty")
    @Pattern(regexp = "^[0-9]{10,12}$",message = "phone number invalid")
    private String phoneNumber;
    @NotNull(message = "zipcode should not be empty")
    @Size(max = 6, message = "Zipcode is invalid.")
    private String zipCode;
}
