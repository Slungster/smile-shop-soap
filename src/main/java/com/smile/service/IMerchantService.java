package com.smile.service;

import com.smile.gs_ws.AddMerchantRequest;
import com.smile.gs_ws.AddMerchantResponse;
import com.smile.gs_ws.DeleteMerchantRequest;
import com.smile.gs_ws.DeleteMerchantResponse;
import com.smile.gs_ws.UpdateMerchantRequest;
import com.smile.gs_ws.UpdateMerchantResponse;

public interface IMerchantService {

    AddMerchantResponse addMerchant(AddMerchantRequest request);

    UpdateMerchantResponse updateMerchant(UpdateMerchantRequest request);

    DeleteMerchantResponse deleteMerchant(DeleteMerchantRequest request);
}
