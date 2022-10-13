package com.vask.ysellbtoheroku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private int id;
    private String text;
    private LocalDateTime dateOfCreate;
    private boolean sent;

}
