package com.bridgelabz.addressbookapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addressBook")
@Data
public class AddressBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String phoneNumber;
    private String zipCode;
}
