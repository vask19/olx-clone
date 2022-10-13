package com.vask.ysellbtoheroku.mapper;
import com.vask.ysellbtoheroku.dto.MessageDto;
import com.vask.ysellbtoheroku.model.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageMapper {
    MessageMapper MAPPER = Mappers.getMapper(MessageMapper.class);


//    @Mapping(target = "sender.id", source = "senderId")
//    @Mapping(target = "recipient.id", source = "recipientId")
    Message toMessage(MessageDto messageDto);

    @InheritInverseConfiguration
//    @Mapping(target = "senderId", source = "sender.id")
//    @Mapping(target = "recipientId", source = "recipient.id")
    MessageDto fromMessage(Message message);


    List<Message> toMessageList(List<MessageDto> messageDtoList);

    List<MessageDto> fromMessageList(List<Message> messageList);


}
