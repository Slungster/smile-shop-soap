package com.smile.service;


import com.smile.gs_ws.*;

public interface IMerchantService {

    AddMerchantResponse addMerchant(AddMerchantRequest request);

    UpdateMerchantResponse updateMerchant(UpdateMerchantRequest request);

    DeleteMerchantResponse deleteMerchant(DeleteMerchantRequest request);
}
