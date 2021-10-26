package com.smile.service;

import com.smile.entity.Product;
import com.smile.gs_ws.*;
import com.smile.mapper.ProductMapper;
import com.smile.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService implements IProductService {
    
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {

        AddProductResponse response = new AddProductResponse();
        ProductsStatus status = new ProductsStatus();

        Product productToCreate = new Product();
        productToCreate.setLabel(request.getLabel());
        productToCreate.setUnitPrice(request.getUnitPrice());
        productToCreate.setCurrency(request.getCurrency());
        productToCreate.setWeight(request.getWeight());
        productToCreate.setHeight(request.getHeight());

        Product created = productRepository.save(productToCreate);

        status.setStatusCode("CREATED");
        status.setMessage("A new product with id " + created.getProductId() + " has been created!");
        response.setProductsStatus(status);
        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request) {

        UpdateProductResponse response = new UpdateProductResponse();
        ProductsStatus status = new ProductsStatus();

        Long inputId = request.getProductDto().getId();

        if (Objects.isNull(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Dto id cannot be null !");
            response.setProductsStatus(status);
            return response;
        }

        if (!productRepository.existsById(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("The product with id " + inputId + " doesn't exist, update impossible!");
            response.setProductsStatus(status);
            return response;
        }

        Product productToUpdate = productMapper.dtoToEntity(request.getProductDto());
        Product updated = productRepository.save(productToUpdate);

        status.setStatusCode("UPDATED");
        status.setMessage("The product with id " + inputId + " has been updated !");
        response.setProductsStatus(status);
        return response;
    }

    @Override
    public DeleteProductResponse deleteProduct(DeleteProductRequest request) {

        DeleteProductResponse response = new DeleteProductResponse();
        ProductsStatus status = new ProductsStatus();

        Long inputId = request.getProductId();

        if (Objects.isNull(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("Dto id cannot be null !");
            response.setProductsStatus(status);
            return response;
        }

        if (!productRepository.existsById(inputId)) {
            status.setStatusCode("ERROR");
            status.setMessage("The product with id " + inputId + " doesn't exist!");
            response.setProductsStatus(status);
            return response;
        }

        productRepository.deleteById(inputId);
        status.setStatusCode("DELETED");
        status.setMessage("The product with id " + inputId + " has been deleted !");
        response.setProductsStatus(status);
        return response;
    }
}
