package com.example.demospring.restapi.mapper;

import com.example.demospring.restapi.api.v1.contract.ProductRequest;
import com.example.demospring.restapi.api.v1.contract.ProductResponse;
import com.example.demospring.restapi.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract Product map(ProductRequest request);

    public abstract void update(ProductRequest request, @MappingTarget Product model);

    public abstract ProductResponse map(Product model);
}
