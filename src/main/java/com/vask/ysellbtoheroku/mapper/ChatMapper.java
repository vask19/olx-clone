package com.vask.ysellbtoheroku.mapper;


import com.vask.ysellbtoheroku.dto.ChatDto;
import com.vask.ysellbtoheroku.dto.MessageDto;
import com.vask.ysellbtoheroku.model.Chat;
import com.vask.ysellbtoheroku.model.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ChatMapper {
    ChatMapper MAPPER = Mappers.getMapper(ChatMapper.class);
    MessageMapper messageMapper = MessageMapper.MAPPER;

    @Mapping(target = "messages",source = "messageDtoList")
    @Mapping(target = "sender.id",source = "senderId")
    @Mapping(target = "recipient.id",source = "recipientId")
    Chat toChat(ChatDto chatDto);

    @InheritInverseConfiguration
    @Mapping(target = "messageDtoList",source = "messages")
    @Mapping(target = "senderId",source = "sender.id")
    @Mapping(target = "recipientId",source = "recipient.id")
    @Mapping(target = "sendersUsername",source = "sender.username")
    @Mapping(target = "recipientsUsername",source = "recipient.username")
    ChatDto fromChat(Chat chat);

    @Mapping(target = "messages",source = "messageDtoList")
    @Mapping(target = "sender.id",source = "senderId")
    @Mapping(target = "recipient.id",source = "recipientId")
    List<Chat> toChatList(List<ChatDto> chatDtoList);

    @InheritInverseConfiguration
    @Mapping(target = "messageDtoList",source = "messages")
    @Mapping(target = "senderId",source = "sender.id")
    @Mapping(target = "recipientId",source = "recipient.id")
    @Mapping(target = "sendersUsername",source = "sender.username")
    @Mapping(target = "recipientsUsername",source = "recipient.username")
    List<ChatDto> fromChatList(List<Chat> chats);


    List<Message> toMessageList(List<MessageDto> messageDtoList);
}
