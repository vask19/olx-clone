package com.vask.ysellbtoheroku.mapper;
import com.vask.ysellbtoheroku.model.User;
import com.vask.ysellbtoheroku.payload.request.SignupRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignupMapper {


    SignupMapper MAPPER = Mappers.getMapper(SignupMapper.class);

    User toUser(SignupRequest signupRequest);

    @InheritInverseConfiguration
    SignupRequest fromUser(User user);
}
