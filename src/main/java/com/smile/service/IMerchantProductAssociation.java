package com.smile.service;

import com.smile.gs_ws.AssociationRequest;
import com.smile.gs_ws.AssociationResponse;

public interface IMerchantProductAssociation {

    AssociationResponse associateMerchantWithProduct (AssociationRequest request);
}
