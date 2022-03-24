package com.redeyefrog.dto.product;

import com.redeyefrog.persistence.entity.ProductDetailEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {

    public ProductDto(ProductDetailEntity entity) {

    }

}
