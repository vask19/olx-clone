package com.vask.ysellbtoheroku.mapper;
import com.vask.ysellbtoheroku.dto.UserDto;
import com.vask.ysellbtoheroku.model.Product;
import com.vask.ysellbtoheroku.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);


    List<Integer> map(List<Product> value);

    default Integer map(Product value){
        return value.getId();
    }

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    @Mapping(target = "productDtoList",source = "products")
    UserDto fromUser(User user);

    List<User> toUserList(List<UserDto> userDtoList);

    @Mapping(target = "productDtoList",source = "products")
    List<UserDto> fromUserList(List<User> users);

}
