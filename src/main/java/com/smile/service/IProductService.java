package com.smile.service;

import com.smile.gs_ws.*;


public interface IProductService {

    AddProductResponse addProduct(AddProductRequest request);

    UpdateProductResponse updateProduct(UpdateProductRequest request);

    DeleteProductResponse deleteProduct(DeleteProductRequest request);

}
