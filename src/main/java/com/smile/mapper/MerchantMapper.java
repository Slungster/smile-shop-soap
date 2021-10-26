package com.smile.mapper;

import com.smile.entity.Address;
import com.smile.entity.Merchant;
import com.smile.gs_ws.MerchantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MerchantMapper {

    private AddressMapper addressMapper;

    @Autowired
    public MerchantMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Merchant dtoToEntity(MerchantDto merchantDto) {

        Merchant merchant = new Merchant();
        merchant.setMerchantId(merchantDto.getId());
        merchant.setName(merchantDto.getName());
        merchant.setLastName(merchantDto.getLastName());
        List<Address> addresses = merchantDto.getAddresses().stream()
                .map(ad -> addressMapper.dtoToEntity(ad)).collect(Collectors.toList());

        addresses.stream().forEach(ad -> ad.setMerchant(merchant));
        merchant.setAddresses(addresses);

        return merchant;
    }

/*    public MerchantDto entityToDto(Merchant merchant) {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setId(merchant.getMerchantId());
        merchantDto.setName(merchant.getName());
        merchantDto.setLastName(merchant.getLastName());
        List<AddressDto> addresses = merchant.getAddresses().stream()
                .map(ad -> addressMapper.entityToDto(ad)).collect(Collectors.toList());
        //merchantDto.setAddresses(addresses);
        return merchantDto;
    }*/

}
