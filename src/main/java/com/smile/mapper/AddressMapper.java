package com.smile.mapper;

import com.smile.entity.Address;
import com.smile.gs_ws.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address dtoToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressId(addressDto.getId());
        address.setNumber(addressDto.getNumber());
        address.setStreet(addressDto.getStreet());
        address.setZipCode(addressDto.getZipcode());
        return address;
    }

    public AddressDto entityToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getAddressId());
        addressDto.setNumber(address.getNumber());
        addressDto.setStreet(address.getStreet());
        addressDto.setZipcode(address.getZipCode());
        return addressDto;
    }
}
