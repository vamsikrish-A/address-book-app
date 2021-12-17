package com.bridgelabz.addressbookapp.builder;
/**
 * @Purpose: used to copy the properties of DTO and Entity class variables
 * @author: Vamsi Krishna
 * @since: 15/12/2021
 */
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
