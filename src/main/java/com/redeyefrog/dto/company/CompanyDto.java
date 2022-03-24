package com.redeyefrog.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redeyefrog.persistence.entity.CompanyEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyDto {

    @ApiModelProperty(name = "COMPANY_ID", value = "COMPANY ID")
    @JsonProperty("COMPANY_ID")
    private String companyId;

    @ApiModelProperty(name = "COMPANY_NAME", value = "COMPANY NAME")
    @JsonProperty("COMPANY_NAME")
    private String companyName;

    @ApiModelProperty(name = "COMPANY_ADDRESS", value = "COMPANY ADDRESS")
    @JsonProperty("COMPANY_ADDRESS")
    private String companyAddress;

    @ApiModelProperty(name = "COMPANY_PHONE", value = "COMPANY PHONE")
    @JsonProperty("COMPANY_PHONE")
    private String companyPhone;

    public CompanyDto(CompanyEntity entity) {
        this.companyId = entity.getId();
        this.companyName = entity.getName();
        this.companyAddress = entity.getAddress();
        this.companyPhone = entity.getPhone();
    }

}
