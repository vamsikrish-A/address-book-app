package com.bridgelabz.addressbookapp.builder;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.AddressBookEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressBookBuilder {
    public AddressBookEntity buildAddressBookEntity(AddressBookDto addressBookDto,
                                                    AddressBookEntity addressBookEntity) {
        BeanUtils.copyProperties(addressBookDto, addressBookEntity);
        return addressBookEntity;
    }
}
