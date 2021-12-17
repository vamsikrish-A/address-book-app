package com.bridgelabz.addressbookapp.entity;
/**
 * @purpose: entity is lightweight persistence object, represents a table in DB, corresponds to row in table.
 * @author: VamsiKrishna
 * @since: 15/12/2021
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addressBook")
@Data
public class AddressBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String firstName;
    private String lastName;
    private String state;
    private String phoneNumber;
    private String zipCode;
}
