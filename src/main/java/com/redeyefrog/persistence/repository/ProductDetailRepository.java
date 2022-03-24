package com.redeyefrog.persistence.repository;

import com.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long>, JpaSpecificationExecutor<ProductDetailEntity> {

}
