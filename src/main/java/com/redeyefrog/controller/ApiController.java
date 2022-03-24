package com.redeyefrog.controller;

import com.redeyefrog.dto.company.CompanyQueryCondition;
import com.redeyefrog.dto.company.CompanyQueryResult;
import com.redeyefrog.dto.product.ProductQueryCondition;
import com.redeyefrog.dto.product.ProductQueryResult;
import com.redeyefrog.persistence.entity.CompanyEntity;
import com.redeyefrog.persistence.entity.CustomerEntity;
import com.redeyefrog.service.ApiService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @ApiOperation(value = "Find Customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "Query Start Date", example = "19600101", required = true),
            @ApiImplicitParam(name = "endDate", value = "Query End Date", example = "19801231", required = true)})
    @PostMapping("/findCustomer")
    public List<CustomerEntity> findCustomer(@RequestParam String startDate, @RequestParam String endDate) {

        return apiService.findByDateRange(startDate, endDate);
    }

    @ApiOperation(value = "Find Company")
    @PostMapping("/findCompany")
    public CompanyQueryResult findCompany(@RequestBody CompanyQueryCondition condition) {

        return apiService.findCompanyByCondition(condition);
    }

    @ApiOperation(value = "Find Company")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "Query Start Date", example = "19700101", required = true),
            @ApiImplicitParam(name = "endDate", value = "Query End Date", example = "19901231", required = true)})
    @PostMapping("/findCompany2")
    public List<CompanyEntity> findCompany2(@RequestParam String startDate, @RequestParam String endDate) {

        return apiService.findCompanyByDateRange(startDate, endDate);
    }

    @ApiOperation(value = "Find Product Detail")
    @PostMapping("/findProductDetail")
    public ProductQueryResult findProductDetail(@RequestBody ProductQueryCondition condition) {

        return apiService.findProductDetailByCondition(condition);
    }

}
