package com.smile.service;

import com.smile.entity.Address;
import com.smile.entity.Merchant;
import com.smile.gs_ws.*;
import com.smile.mapper.AddressMapper;
import com.smile.mapper.MerchantMapper;
import com.smile.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class MerchantService implements IMerchantService {

    private MerchantRepository merchantRepository;
    private MerchantMapper merchantMapper;
    private AddressMapper addressMapper;

    @Autowired
    public MerchantService(MerchantRepository merchantRepository, MerchantMapper merchantMapper, AddressMapper addressMapper) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddMerchantResponse addMerchant(AddMerchantRequest request) {

        AddMerchantResponse response = new AddMerchantResponse();
        MerchantsStatus status = new MerchantsStatus();

        Merchant merchantToCreate = new Merchant();
        merchantToCreate.setName(request.getName());
        merchantToCreate.setLastName(request.getLastName());
        List<Address> addresses = request.getAddresses().stream()
                .map(ad -> addressMapper.dtoToEntity(ad)).collect(Collectors.toList());
        addresses.stream().forEach(ad -> ad.setMerchant(merchantToCreate));
        merchantToCreate.setAddresses(addresses);

        Merchant created = merchantRepository.saveAndFlush(merchantToCreate);

        status.setStatusCode("CREATED");
        status.setMessage("A new merchant with id " + created.getMerchantId() + " has been created!");
        response.setMerchantsStatus(status);
        return response;
    }

    @Override
    public UpdateMerchantResponse updateMerchant(UpdateMerchantRequest request) {

        UpdateMerchantResponse response = new UpdateMerchantResponse();
        MerchantsStatus status = new MerchantsStatus();

        Long inputId = request.getMerchantDto().getId();

        if (Objects.isNull(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Dto id cannot be null !");
            response.setMerchantsStatus(status);
            return response;
        }

        if (!merchantRepository.existsById(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("The merchant with id " + inputId + " doesn't exist, update impossible!");
            response.setMerchantsStatus(status);
            return response;
        }

        Merchant merchantToUpdate = merchantMapper.dtoToEntity(request.getMerchantDto());
        merchantRepository.save(merchantToUpdate);

        status.setStatusCode("UPDATED");
        status.setMessage("The merchant with id " + inputId + " has been updated !");
        response.setMerchantsStatus(status);
        return response;
    }

    @Override
    public DeleteMerchantResponse deleteMerchant(DeleteMerchantRequest request) {

        DeleteMerchantResponse response = new DeleteMerchantResponse();
        MerchantsStatus status = new MerchantsStatus();

        Long inputId = request.getMerchantId();

        if (Objects.isNull(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Dto id cannot be null !");
            response.setMerchantsStatus(status);
            return response;
        }

        if (!merchantRepository.existsById(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("The merchant with id " + inputId + " doesn't exist!");
            response.setMerchantsStatus(status);
            return response;
        }

        merchantRepository.deleteById(inputId);
        status.setStatusCode("DELETED");
        status.setMessage("The merchant with id " + inputId + " has been deleted !");
        response.setMerchantsStatus(status);
        return response;
    }
}
