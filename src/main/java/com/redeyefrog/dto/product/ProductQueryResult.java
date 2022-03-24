package com.redeyefrog.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductQueryResult {

    @ApiModelProperty(name = "QUERY_RESULT", value = "QUERY RESULT")
    @JsonProperty("QUERY_RESULT")
    private List<ProductDto> productList;

}
