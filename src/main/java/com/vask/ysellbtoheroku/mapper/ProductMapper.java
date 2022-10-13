package com.vask.ysellbtoheroku.mapper;
import com.vask.ysellbtoheroku.dto.ProductDto;
import com.vask.ysellbtoheroku.model.Image;
import com.vask.ysellbtoheroku.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    default List<Long> map(List<Image> value){
        return value.stream().map(Image::getId).collect(Collectors.toList());
    }

    default Long map(Image value){
        return value.getId();
    }

    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "imageIds",source = "images")
    @Mapping(target = "ownerUsername",source = "user.username")
    ProductDto fromProduct(Product product);



    List<Product> toProductList(List<ProductDto> productDtoList);

    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "imageIds",source = "images")
    @Mapping(target = "ownerUsername",source = "user.username")
    List<ProductDto> fromProductList(List<Product> products);



}
