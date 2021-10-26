package com.smile.endpoints;

import com.smile.gs_ws.AddMerchantRequest;
import com.smile.gs_ws.AddMerchantResponse;
import com.smile.gs_ws.DeleteMerchantRequest;
import com.smile.gs_ws.DeleteMerchantResponse;
import com.smile.gs_ws.UpdateMerchantRequest;
import com.smile.gs_ws.UpdateMerchantResponse;
import com.smile.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MerchantEndpoint {

    private static final String NAMESPACE_URI = "http://www.smileshop.com/shop-ws";
    
    private MerchantService merchantService;

    @Autowired
    public MerchantEndpoint(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMerchantRequest")
    @ResponsePayload
    public AddMerchantResponse addMerchant(@RequestPayload AddMerchantRequest request) {
        return merchantService.addMerchant(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMerchantRequest")
    @ResponsePayload
    public UpdateMerchantResponse updateMerchant(@RequestPayload UpdateMerchantRequest request) {
        return merchantService.updateMerchant(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMerchantRequest")
    @ResponsePayload
    public DeleteMerchantResponse deleteMerchant(@RequestPayload DeleteMerchantRequest request) {
        return merchantService.deleteMerchant(request);
    }
}
