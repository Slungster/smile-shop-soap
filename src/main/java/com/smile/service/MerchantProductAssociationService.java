package com.smile.service;

import com.smile.entity.Merchant;
import com.smile.entity.MerchantProduct;
import com.smile.entity.Product;
import com.smile.gs_ws.AssociationRequest;
import com.smile.gs_ws.AssociationResponse;
import com.smile.gs_ws.AssociationStatus;
import com.smile.repository.MerchantProductRepository;
import com.smile.repository.MerchantRepository;
import com.smile.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class MerchantProductAssociationService implements IMerchantProductAssociation {

    private MerchantRepository merchantRepository;
    private ProductRepository productRepository;
    private MerchantProductRepository merchantProductRepository;

    @Autowired
    public MerchantProductAssociationService(MerchantRepository merchantRepository, ProductRepository productRepository) {
        this.merchantRepository = merchantRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public AssociationResponse associateMerchantWithProduct(AssociationRequest request) {

        AssociationResponse response = new AssociationResponse();
        AssociationStatus status = new AssociationStatus();

        Long inputMerchantId = request.getMerchantId();
        Long inputProductId = request.getProductId();

        if (Objects.isNull(inputMerchantId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Merchant id cannot be null !");
            response.setAssociationStatus(status);
            return response;
        }

        if (Objects.isNull(inputProductId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Product id cannot be null !");
            response.setAssociationStatus(status);
            return response;
        }

        if (!merchantRepository.existsById(inputMerchantId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Merchant doesn't exist !");
            response.setAssociationStatus(status);
            return response;
        }

        if (!productRepository.existsById(inputProductId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Product doesn't exist !");
            response.setAssociationStatus(status);
            return response;
        }

        Merchant merchant = merchantRepository.getById(inputMerchantId);
        Product product = productRepository.getById(inputProductId);

        if ((merchant.getMerchantProduct().stream().filter(mpr -> mpr.getProduct().getProductId().equals(inputProductId)).findAny()).isPresent()) {
            status.setStatusCode("INFORMATION");
            status.setMessage("The product with id " + inputProductId + " is already associated to merchant id " + inputMerchantId + " !");
            response.setAssociationStatus(status);
            return response;
        }

/*        MerchantProduct association = new MerchantProduct(null, merchant, product, new Date());
        Set<Merchant> merchantSet = new HashSet<>();
        Set<Product> productSet = new HashSet<>();
        merchantSet.add(merchant);
        productSet.add(product);
        merchantProductRepository.save(association);*/

        MerchantProduct association = new MerchantProduct();
        association.setAssociationDate(new Date());
        association.setMerchant(merchant);
        association.setProduct(product);

        Set<MerchantProduct> associationSet = new HashSet<>();
        associationSet.add(association);
        merchant.setMerchantProduct(associationSet);

        status.setStatusCode("ASSOCIATED");
        status.setMessage("The product with id " + inputProductId + " has been associated to merchant id " + inputMerchantId + " !");
        response.setAssociationStatus(status);

        return response;
    }
}
