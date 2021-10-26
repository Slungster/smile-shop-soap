package com.smile.mapper;

import com.smile.entity.Product;
import com.smile.gs_ws.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getId());
        product.setCurrency(productDto.getCurrency());
        product.setLabel(productDto.getLabel());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setHeight(productDto.getHeight());
        product.setWeight(productDto.getWeight());
        return product;
    }

/*    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getProductId());
        productDto.setCurrency(product.getCurrency());
        productDto.setLabel(product.getLabel());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setHeight(product.getHeight());
        productDto.setWeight(product.getWeight());
        return productDto;
    }*/
}
