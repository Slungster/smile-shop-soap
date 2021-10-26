package com.smile.repository;

import com.smile.entity.MerchantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductRepository extends JpaRepository<MerchantProduct,Long> {

    @Override
    <S extends MerchantProduct> S save(S entity);
}
