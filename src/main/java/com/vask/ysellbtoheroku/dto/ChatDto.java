package com.vask.ysellbtoheroku.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private int id;
    private int senderId;
    private int recipientId;
    private String sendersUsername;
    private String recipientsUsername;
    private List<MessageDto> messageDtoList;
    private Long chatImageId;
    private String productDescription;


}
