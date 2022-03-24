package com.redeyefrog.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CompanyQueryCondition {

    @ApiModelProperty(name = "COMPANY_ID", value = "COMPANY ID", example = "12345678", required = true)
    @JsonProperty("COMPANY_ID")
    private String companyId;

    @ApiModelProperty(name = "COMPANY_NAME", value = "COMPANY NAME", example = "OO公司")
    @JsonProperty("COMPANY_NAME")
    private String companyName;

}
