package com.smile.endpoints;

import com.smile.gs_ws.AssociationRequest;
import com.smile.gs_ws.AssociationResponse;
import com.smile.service.MerchantProductAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MerchantProductAssociationEndpoint {

    private static final String NAMESPACE_URI = "http://www.smileshop.com/shop-ws";

    private MerchantProductAssociationService merchantProductAssociationService;

    @Autowired
    public MerchantProductAssociationEndpoint(MerchantProductAssociationService merchantProductAssociationService) {
        this.merchantProductAssociationService = merchantProductAssociationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "associationRequest")
    @ResponsePayload
    public AssociationResponse associateMerchantWithProduct(@RequestPayload AssociationRequest request) {
        return merchantProductAssociationService.associateMerchantWithProduct(request);
    }

}
