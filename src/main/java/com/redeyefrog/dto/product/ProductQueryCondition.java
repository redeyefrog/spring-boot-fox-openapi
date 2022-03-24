package com.redeyefrog.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductQueryCondition {

    @ApiModelProperty(name = "MASTER_ID", value = "PRODUCT MASTER ID", example = "OO公司")
    @JsonProperty("MASTER_ID")
    private Long masterId;

    @ApiModelProperty(name = "COMPANY_NAME", value = "COMPANY NAME", example = "OO公司")
    @JsonProperty("COMPANY_NAME")
    private String companyName;

}
