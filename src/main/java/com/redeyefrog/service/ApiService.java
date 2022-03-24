package com.redeyefrog.service;

import com.redeyefrog.dto.company.CompanyDto;
import com.redeyefrog.dto.company.CompanyQueryCondition;
import com.redeyefrog.dto.company.CompanyQueryResult;
import com.redeyefrog.dto.product.ProductDto;
import com.redeyefrog.dto.product.ProductQueryCondition;
import com.redeyefrog.dto.product.ProductQueryResult;
import com.redeyefrog.persistence.entity.CompanyEntity;
import com.redeyefrog.persistence.entity.CustomerEntity;
import com.redeyefrog.persistence.entity.ProductDetailEntity;
import com.redeyefrog.persistence.entity.ProductMasterEntity;
import com.redeyefrog.persistence.repository.CompanyRepository;
import com.redeyefrog.persistence.repository.CustomerRepository;
import com.redeyefrog.persistence.repository.ProductDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApiService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private ProductDetailRepository productDetailRepo;

    public List<CustomerEntity> findByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        return customerRepo.findByBirthdayBetween(start, end);
    }

    public List<CompanyEntity> findCompanyByDateRange(String startDate, String endDate) {
        Specification<CompanyEntity> companySpec = (root, query, builder) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDateTime start = LocalDate.parse(startDate, formatter).atTime(LocalTime.MIN);
            LocalDateTime end = LocalDate.parse(endDate, formatter).atTime(LocalTime.MAX);
            root.join("id");

            return builder.between(root.get("createDate"), start, end);
        };

        return companyRepo.findAll(companySpec);
    }

    public CompanyQueryResult findCompanyByCondition(CompanyQueryCondition condition) {
        Specification<CompanyEntity> conditionSpec = findCompanyQueryConditionSpec(condition);

        List<CompanyDto> companyDtoList = companyRepo.findAll(conditionSpec).stream().map(entity -> new CompanyDto(entity)).collect(Collectors.toList());

        return new CompanyQueryResult(companyDtoList);
    }

    private Specification<CompanyEntity> findCompanyQueryConditionSpec(CompanyQueryCondition condition) {
        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.isNotBlank(condition.getCompanyId())) {
                predicateList.add(builder.equal(root.get("id"), condition.getCompanyId()));
            }

            if (StringUtils.isNotBlank(condition.getCompanyName())) {
                predicateList.add(builder.like(root.get("name"), MessageFormat.format("%{0}%", condition.getCompanyName().toUpperCase())));
            }

            return query.where(predicateList.toArray(Predicate[]::new)).getRestriction();
        };
    }

    public ProductQueryResult findProductDetailByCondition(ProductQueryCondition condition) {
        Specification<ProductDetailEntity> productDetailSpec = findProductDetailSpec(condition);

        List<ProductDetailEntity> productDetailEntityList = productDetailRepo.findAll(productDetailSpec);
        log.info("productDetailEntityList: {}", productDetailEntityList);

        List<ProductDto> productDtoList = productDetailEntityList.stream().map(entity -> new ProductDto(entity)).collect(Collectors.toList());

        return new ProductQueryResult(productDtoList);
    }

    private Specification<ProductDetailEntity> findProductDetailSpec(ProductQueryCondition condition) {
        return (root, query, builder) -> {
            // for use count
            if (query.getResultType().equals(Long.class) || query.getResultType().equals(long.class)) {
                // 僅連表查詢，返回的主實體的所有屬性，可以理解為 SELECT a.*
                Join<ProductDetailEntity, ProductMasterEntity> productJoin = root.join("productMaster", JoinType.LEFT);
            } else {
                // 連表查詢 + 快載入，返回連表所有實體的屬性，可以理解為 SELECT *
                Fetch<ProductDetailEntity, ProductMasterEntity> productFetch = root.fetch("productMaster", JoinType.LEFT);
            }

            List<Predicate> predicateList = new ArrayList<>();

            if (condition.getMasterId() != null) {
                predicateList.add(builder.equal(root.get("productMaster").get("masterId"), condition.getMasterId()));
            }

            if (StringUtils.isNotBlank(condition.getCompanyName())) {
                predicateList.add(builder.like(root.get("productMaster").get("companyName"), MessageFormat.format("%{0}%", condition.getCompanyName().toUpperCase())));
            }

            return query.where(predicateList.toArray(Predicate[]::new)).getRestriction();
        };
    }

}
