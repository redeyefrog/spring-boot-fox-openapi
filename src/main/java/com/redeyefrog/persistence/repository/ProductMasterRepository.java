package com.redeyefrog.persistence.repository;

import com.redeyefrog.persistence.entity.ProductMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductMasterRepository extends JpaRepository<ProductMasterEntity, Long>, JpaSpecificationExecutor<ProductMasterEntity> {

}
