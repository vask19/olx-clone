package com.vask.ysellbtoheroku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiErrorDto {
    private String message;
    private HttpStatus status;
    private LocalDateTime timeStamp;
}
