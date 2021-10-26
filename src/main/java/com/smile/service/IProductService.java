package com.smile.service;

import com.smile.gs_ws.AddProductRequest;
import com.smile.gs_ws.AddProductResponse;
import com.smile.gs_ws.DeleteProductRequest;
import com.smile.gs_ws.DeleteProductResponse;
import com.smile.gs_ws.UpdateProductRequest;
import com.smile.gs_ws.UpdateProductResponse;

public interface IProductService {

    AddProductResponse addProduct(AddProductRequest request);

    UpdateProductResponse updateProduct(UpdateProductRequest request);

    DeleteProductResponse deleteProduct(DeleteProductRequest request);

}
